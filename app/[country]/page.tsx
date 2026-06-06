import { notFound } from 'next/navigation'
import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'
import TopicCard from '@/components/TopicCard'
import CountryCard from '@/components/CountryCard'
import QuickStatsBar from '@/components/QuickStatsBar'
import {
  getAllCountrySlugs,
  getCountryData,
  getSiteIndex,
  getRelatedCountries,
  getCountryStats,
} from '@/lib/getData'

interface Props {
  params: Promise<{ country: string }>
}

export async function generateStaticParams() {
  return getAllCountrySlugs().map((slug) => ({ country: slug }))
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { country: slug } = await params
  const country = getCountryData(slug)
  if (!country) return {}

  const title = `How Things Work in ${country.country} — Travel Guide`
  const description = `Everything tourists need to know about ${country.country}: tipping, transport, healthcare, local laws, dress codes, and more. Verified practical guides.`

  return {
    title,
    description,
    alternates: { canonical: `/${slug}` },
    openGraph: { title, description, url: `/${slug}`, type: 'website' },
  }
}

export default async function CountryPage({ params }: Props) {
  const { country: slug } = await params
  const country = getCountryData(slug)
  if (!country) notFound()

  const { topics: topicIndex } = getSiteIndex()
  const relatedCountries = getRelatedCountries(slug, country.region, 4)
  const stats = getCountryStats(country)

  const breadcrumbSchema = {
    '@context': 'https://schema.org',
    '@type': 'BreadcrumbList',
    itemListElement: [
      { '@type': 'ListItem', position: 1, name: 'Home', item: '/' },
      { '@type': 'ListItem', position: 2, name: country.country, item: `/${slug}` },
    ],
  }

  return (
    <>
      <script
        type="application/ld+json"
        dangerouslySetInnerHTML={{ __html: JSON.stringify(breadcrumbSchema) }}
      />

      <Navigation />

      <main className="min-h-screen bg-gray-50">
        {/* Breadcrumb */}
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-3">
            <nav className="flex items-center gap-2 text-sm text-gray-500">
              <Link href="/" className="hover:text-teal-600 transition-colors">Home</Link>
              <span>/</span>
              <Link href="/countries" className="hover:text-teal-600 transition-colors">Countries</Link>
              <span>/</span>
              <span className="text-gray-900 font-medium">{country.country}</span>
            </nav>
          </div>
        </div>

        {/* Country hero */}
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-10">
            <div className="flex flex-col sm:flex-row items-start sm:items-center gap-6">
              <span className="text-7xl">{country.emoji}</span>
              <div>
                <h1 className="text-4xl font-extrabold text-gray-900 mb-2">
                  How Things Work in {country.country}
                </h1>
                <div className="flex flex-wrap gap-3 text-sm">
                  <span className="flex items-center gap-1.5 bg-gray-100 text-gray-700 px-3 py-1 rounded-full">
                    🌏 {country.region}
                  </span>
                  <span className="flex items-center gap-1.5 bg-gray-100 text-gray-700 px-3 py-1 rounded-full">
                    💱 {country.currency}
                  </span>
                  <span className="flex items-center gap-1.5 bg-gray-100 text-gray-700 px-3 py-1 rounded-full">
                    🗣️ {country.language}
                  </span>
                  <span className="flex items-center gap-1.5 bg-teal-100 text-teal-700 px-3 py-1 rounded-full font-medium">
                    📋 {Object.keys(country.topics).length} topics covered
                  </span>
                </div>
                <QuickStatsBar stats={stats} />
              </div>
            </div>
          </div>
        </div>

        <div className="container-main py-8">
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
            {/* Topic grid */}
            <div className="lg:col-span-2">
              <h2 className="text-xl font-bold text-gray-900 mb-5">
                Everything Tourists Need to Know
              </h2>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                {topicIndex.map((topicMeta) => {
                  const entry = country.topics[topicMeta.slug]
                  if (!entry) return null
                  return (
                    <TopicCard
                      key={topicMeta.slug}
                      topic={topicMeta}
                      countrySlug={slug}
                      topicEntry={entry}
                      variant="country"
                    />
                  )
                })}
              </div>
            </div>

            {/* Sidebar */}
            <div>
              <h2 className="text-base font-bold text-gray-900 mb-4">Similar Countries</h2>
              <div className="grid gap-3">
                {relatedCountries.map((c) => (
                  <CountryCard key={c.slug} country={{
                    name: c.country,
                    slug: c.slug,
                    emoji: c.emoji,
                    region: c.region,
                    topicCount: Object.keys(c.topics).length,
                    mostSearched: [],
                  }} variant="compact" />
                ))}
              </div>

              <div className="mt-6 bg-teal-50 rounded-2xl border border-teal-100 p-5">
                <p className="text-sm font-semibold text-teal-800 mb-1">Browse by topic</p>
                <p className="text-xs text-teal-700 mb-3">See how a topic compares across all countries</p>
                <div className="grid grid-cols-2 gap-2">
                  {topicIndex.slice(0, 4).map((t) => (
                    <Link
                      key={t.slug}
                      href={`/topics/${t.slug}`}
                      className="flex items-center gap-1.5 text-xs bg-white rounded-lg px-2 py-1.5 border border-teal-100 text-teal-700 hover:bg-teal-100 transition-colors"
                    >
                      <span>{t.icon}</span>
                      <span className="truncate">{t.title}</span>
                    </Link>
                  ))}
                </div>
                <Link href="/topics" className="block mt-2 text-center text-xs text-teal-600 hover:text-teal-700 font-medium">
                  View all topics →
                </Link>
              </div>
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
