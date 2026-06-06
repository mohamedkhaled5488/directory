import { notFound } from 'next/navigation'
import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'
import { getCompareData, getQuickAnswerSentiment } from '@/lib/getData'

interface Props {
  params: Promise<{ country1: string; country2: string }>
}

// The 20 most-searched country pairs
const COMPARE_PAIRS: [string, string][] = [
  ['japan', 'thailand'],
  ['japan', 'south-korea'],
  ['uae', 'qatar'],
  ['france', 'italy'],
  ['spain', 'portugal'],
  ['greece', 'turkey'],
  ['thailand', 'vietnam'],
  ['thailand', 'indonesia'],
  ['us', 'uk'],
  ['australia', 'new-zealand'],
  ['india', 'sri-lanka'],
  ['mexico', 'colombia'],
  ['germany', 'netherlands'],
  ['czech-republic', 'hungary'],
  ['brazil', 'argentina'],
  ['japan', 'china'],
  ['singapore', 'malaysia'],
  ['morocco', 'egypt'],
  ['japan', 'germany'],
  ['thailand', 'malaysia'],
]

export async function generateStaticParams() {
  return COMPARE_PAIRS.map(([c1, c2]) => ({ country1: c1, country2: c2 }))
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { country1, country2 } = await params
  const data = getCompareData(country1, country2)
  if (!data) return {}
  const { country1: c1, country2: c2 } = data
  const title = `${c1.country} vs ${c2.country} — Side-by-Side Travel Comparison`
  const description = `Compare ${c1.country} and ${c2.country} across 25 topics: tipping, transport, local laws, safety, food, and more.`
  return {
    title,
    description,
    alternates: { canonical: `/compare/${country1}/${country2}` },
    openGraph: { title, description, url: `/compare/${country1}/${country2}`, type: 'website' },
  }
}

const sentimentBg = { green: 'bg-emerald-50', yellow: 'bg-amber-50', red: 'bg-red-50' }
const sentimentText = { green: 'text-emerald-700', yellow: 'text-amber-700', red: 'text-red-700' }
const sentimentBadge = { green: 'badge-green', yellow: 'badge-yellow', red: 'badge-red' }

export default async function ComparePage({ params }: Props) {
  const { country1: slug1, country2: slug2 } = await params
  const data = getCompareData(slug1, slug2)
  if (!data) notFound()
  const { country1, country2, topicIndex } = data

  return (
    <>
      <Navigation />
      <main className="min-h-screen bg-gray-50">
        {/* Breadcrumb */}
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-3">
            <nav className="flex items-center gap-2 text-sm text-gray-500">
              <Link href="/" className="hover:text-teal-600">Home</Link>
              <span>/</span>
              <span className="text-gray-900 font-medium">Compare</span>
            </nav>
          </div>
        </div>

        {/* Hero */}
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-8">
            <p className="text-sm font-medium text-teal-600 uppercase tracking-wide mb-2">Side-by-Side Comparison</p>
            <h1 className="text-3xl sm:text-4xl font-extrabold text-gray-900 mb-4">
              {country1.emoji} {country1.country} vs {country2.emoji} {country2.country}
            </h1>
            <div className="grid grid-cols-2 gap-4 max-w-md">
              <Link href={`/${slug1}`} className="flex items-center gap-2 p-3 rounded-xl border border-gray-100 hover:border-teal-200 bg-white transition-all">
                <span className="text-3xl">{country1.emoji}</span>
                <div>
                  <p className="font-bold text-sm text-gray-900">{country1.country}</p>
                  <p className="text-xs text-gray-500">{country1.region}</p>
                </div>
              </Link>
              <Link href={`/${slug2}`} className="flex items-center gap-2 p-3 rounded-xl border border-gray-100 hover:border-teal-200 bg-white transition-all">
                <span className="text-3xl">{country2.emoji}</span>
                <div>
                  <p className="font-bold text-sm text-gray-900">{country2.country}</p>
                  <p className="text-xs text-gray-500">{country2.region}</p>
                </div>
              </Link>
            </div>
          </div>
        </div>

        {/* Comparison table */}
        <div className="container-main py-8">
          <div className="space-y-3">
            {topicIndex.map((topicMeta) => {
              const t1 = country1.topics[topicMeta.slug]
              const t2 = country2.topics[topicMeta.slug]
              if (!t1 && !t2) return null
              const s1 = t1 ? getQuickAnswerSentiment(t1.warning, t1.quick_answer) : 'green'
              const s2 = t2 ? getQuickAnswerSentiment(t2.warning, t2.quick_answer) : 'green'

              return (
                <div key={topicMeta.slug} className="bg-white rounded-2xl border border-gray-100 overflow-hidden">
                  {/* Topic header */}
                  <div className="px-5 py-3 bg-gray-50 border-b border-gray-100 flex items-center gap-2">
                    <span className="text-lg">{topicMeta.icon}</span>
                    <h2 className="font-bold text-sm text-gray-900">{topicMeta.title}</h2>
                    {topicMeta.hot && <span className="text-sm">🔥</span>}
                  </div>

                  {/* Side by side */}
                  <div className="grid grid-cols-1 sm:grid-cols-2 divide-y sm:divide-y-0 sm:divide-x divide-gray-100">
                    {/* Country 1 */}
                    <Link href={`/${slug1}/${topicMeta.slug}`} className={`p-5 hover:bg-gray-50 transition-colors ${t1 ? sentimentBg[s1] + '/30' : ''}`}>
                      <div className="flex items-center justify-between mb-2">
                        <span className="flex items-center gap-1.5 font-semibold text-sm text-gray-900">
                          <span>{country1.emoji}</span> {country1.country}
                        </span>
                        {t1 && <span className={sentimentBadge[s1]}>{s1 === 'red' ? 'Warning' : s1 === 'yellow' ? 'Caution' : 'Normal'}</span>}
                      </div>
                      {t1 ? (
                        <>
                          <p className={`text-sm leading-relaxed ${sentimentText[s1]}`}>{t1.quick_answer}</p>
                          {t1.warning && (
                            <p className="mt-1.5 text-xs text-red-600 flex items-start gap-1">
                              <span className="flex-shrink-0">⛔</span>
                              <span className="line-clamp-2">{t1.warning}</span>
                            </p>
                          )}
                          <p className="mt-2 text-xs text-teal-600 font-medium">Full guide →</p>
                        </>
                      ) : (
                        <p className="text-sm text-gray-400 italic">Not available</p>
                      )}
                    </Link>

                    {/* Country 2 */}
                    <Link href={`/${slug2}/${topicMeta.slug}`} className={`p-5 hover:bg-gray-50 transition-colors ${t2 ? sentimentBg[s2] + '/30' : ''}`}>
                      <div className="flex items-center justify-between mb-2">
                        <span className="flex items-center gap-1.5 font-semibold text-sm text-gray-900">
                          <span>{country2.emoji}</span> {country2.country}
                        </span>
                        {t2 && <span className={sentimentBadge[s2]}>{s2 === 'red' ? 'Warning' : s2 === 'yellow' ? 'Caution' : 'Normal'}</span>}
                      </div>
                      {t2 ? (
                        <>
                          <p className={`text-sm leading-relaxed ${sentimentText[s2]}`}>{t2.quick_answer}</p>
                          {t2.warning && (
                            <p className="mt-1.5 text-xs text-red-600 flex items-start gap-1">
                              <span className="flex-shrink-0">⛔</span>
                              <span className="line-clamp-2">{t2.warning}</span>
                            </p>
                          )}
                          <p className="mt-2 text-xs text-teal-600 font-medium">Full guide →</p>
                        </>
                      ) : (
                        <p className="text-sm text-gray-400 italic">Not available</p>
                      )}
                    </Link>
                  </div>
                </div>
              )
            })}
          </div>

          {/* More comparisons */}
          <div className="mt-12 pt-8 border-t border-gray-100">
            <h2 className="text-lg font-bold text-gray-900 mb-4">More Comparisons</h2>
            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 gap-3">
              {COMPARE_PAIRS.filter(([c1, c2]) => c1 !== slug1 || c2 !== slug2).slice(0, 8).map(([c1, c2]) => {
                const d = getCompareData(c1, c2)
                if (!d) return null
                return (
                  <Link
                    key={`${c1}-${c2}`}
                    href={`/compare/${c1}/${c2}`}
                    className="flex items-center gap-2 p-3 rounded-xl border border-gray-100 bg-white hover:border-teal-200 hover:bg-teal-50 transition-all text-sm"
                  >
                    <span>{d.country1.emoji}</span>
                    <span className="text-gray-400 text-xs">vs</span>
                    <span>{d.country2.emoji}</span>
                    <span className="text-gray-700 text-xs font-medium truncate">
                      {d.country1.country} vs {d.country2.country}
                    </span>
                  </Link>
                )
              })}
            </div>
          </div>
        </div>
      </main>

      <footer className="bg-white border-t border-gray-100 mt-12">
        <div className="container-main py-8">
          <div className="flex flex-col sm:flex-row items-center justify-between gap-4">
            <Link href="/" className="flex items-center gap-2 font-bold text-lg text-navy-950">
              <span>🌍</span><span>WorldHow</span>
            </Link>
            <div className="flex gap-4 text-sm text-gray-500">
              <Link href="/countries" className="hover:text-teal-600">Countries</Link>
              <Link href="/topics" className="hover:text-teal-600">Topics</Link>
            </div>
          </div>
        </div>
      </footer>
    </>
  )
}
