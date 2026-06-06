import { notFound } from 'next/navigation'
import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'
import { getSiteIndex, getTopicAcrossCountries, getQuickAnswerSentiment } from '@/lib/getData'

interface Props {
  params: Promise<{ topic: string }>
}

export async function generateStaticParams() {
  const { topics } = getSiteIndex()
  return topics.map((t) => ({ topic: t.slug }))
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { topic: topicSlug } = await params
  const { topics } = getSiteIndex()
  const topicMeta = topics.find((t) => t.slug === topicSlug)
  if (!topicMeta) return {}

  const title = `${topicMeta.title} Rules in Every Country — Compared`
  const description = `${topicMeta.description} See how ${topicMeta.title.toLowerCase()} works across 50 countries including Japan, UAE, Thailand, Germany, the UK, France and more.`

  return {
    title,
    description,
    alternates: { canonical: `/topics/${topicSlug}` },
    openGraph: { title, description, url: `/topics/${topicSlug}`, type: 'website' },
  }
}

const sentimentConfig = {
  green:  { badge: 'badge-green',  label: 'Normal'  },
  yellow: { badge: 'badge-yellow', label: 'Caution' },
  red:    { badge: 'badge-red',    label: 'Warning'  },
}

export default async function TopicOverviewPage({ params }: Props) {
  const { topic: topicSlug } = await params
  const { topics } = getSiteIndex()
  const topicMeta = topics.find((t) => t.slug === topicSlug)
  if (!topicMeta) notFound()

  const countryTopics = getTopicAcrossCountries(topicSlug)

  return (
    <>
      <Navigation />

      <main className="min-h-screen bg-gray-50">
        {/* Breadcrumb */}
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-3">
            <nav className="flex items-center gap-2 text-sm text-gray-500">
              <Link href="/" className="hover:text-teal-600 transition-colors">Home</Link>
              <span>/</span>
              <Link href="/topics" className="hover:text-teal-600 transition-colors">Topics</Link>
              <span>/</span>
              <span className="text-gray-900 font-medium">{topicMeta.title}</span>
            </nav>
          </div>
        </div>

        {/* Hero */}
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-10">
            <div className="flex items-center gap-4 mb-4">
              <span className="text-6xl">{topicMeta.icon}</span>
              <div>
                <h1 className="text-3xl sm:text-4xl font-extrabold text-gray-900">
                  {topicMeta.title} Rules in Every Country
                </h1>
                <p className="text-gray-500 mt-1">{topicMeta.description}</p>
              </div>
            </div>
            <div className="flex items-center gap-4 text-sm text-gray-500">
              <span className="badge-green">Normal</span>
              <span className="badge-yellow">Caution</span>
              <span className="badge-red">Warning</span>
              <span className="text-gray-400">— colour coding based on content</span>
            </div>
          </div>
        </div>

        <div className="container-main py-8">
          <div className="grid gap-4">
            {countryTopics.map(({ country, topic }) => {
              const sentiment = getQuickAnswerSentiment(topic.warning, topic.quick_answer)
              const sc = sentimentConfig[sentiment]

              return (
                <Link
                  key={country.slug}
                  href={`/${country.slug}/${topicSlug}`}
                  className="group flex flex-col sm:flex-row sm:items-start gap-4 p-5 rounded-2xl border border-gray-100 bg-white hover:border-teal-200 hover:shadow-sm transition-all"
                >
                  <div className="flex items-center gap-3 sm:w-40 flex-shrink-0">
                    <span className="text-4xl">{country.emoji}</span>
                    <div>
                      <p className="font-bold text-gray-900 group-hover:text-teal-700 transition-colors">
                        {country.country}
                      </p>
                      <p className="text-xs text-gray-400">{country.region}</p>
                    </div>
                  </div>

                  <div className="flex-1 min-w-0">
                    <div className="flex items-start justify-between gap-3">
                      <p className="text-gray-700 leading-relaxed text-sm">{topic.quick_answer}</p>
                      <span className={`${sc.badge} flex-shrink-0 ml-2`}>{sc.label}</span>
                    </div>
                    {topic.warning && (
                      <p className="mt-2 text-xs text-red-600 flex items-center gap-1">
                        <span>⛔</span>
                        <span className="line-clamp-1">{topic.warning}</span>
                      </p>
                    )}
                  </div>

                  <div className="flex-shrink-0 self-center">
                    <span className="text-sm font-medium text-teal-600 group-hover:text-teal-700 flex items-center gap-1">
                      Full guide <span>→</span>
                    </span>
                  </div>
                </Link>
              )
            })}
          </div>

          {/* Browse other topics */}
          <div className="mt-12">
            <h2 className="text-xl font-bold text-gray-900 mb-5">Browse Other Topics</h2>
            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-5 gap-3">
              {topics.filter((t) => t.slug !== topicSlug).map((t) => (
                <Link
                  key={t.slug}
                  href={`/topics/${t.slug}`}
                  className="flex flex-col items-center p-4 rounded-xl border border-gray-100 bg-white hover:border-teal-200 hover:bg-teal-50 transition-all text-center"
                >
                  <span className="text-3xl mb-2">{t.icon}</span>
                  <span className="text-xs font-semibold text-gray-700">{t.title}</span>
                </Link>
              ))}
            </div>
          </div>
        </div>
      </main>

      <footer className="bg-white border-t border-gray-100 mt-12">
        <div className="container-main py-8">
          <div className="flex flex-col sm:flex-row items-center justify-between gap-4">
            <Link href="/" className="flex items-center gap-2 font-bold text-lg text-navy-950">
              <span>🌍</span><span>KnowBefore</span>
            </Link>
            <p className="text-sm text-gray-500">Practical travel knowledge for every country.</p>
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
