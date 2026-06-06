import { notFound } from 'next/navigation'
import type { Metadata } from 'next'
import type { CountryData, TopicEntry } from '@/lib/types'
import Navigation from '@/components/Navigation'
import QuickAnswerBox from '@/components/QuickAnswerBox'
import TouristTips from '@/components/TouristTips'
import WarningBox from '@/components/WarningBox'
import CountryCompare from '@/components/CountryCompare'
import AffiliateCards from '@/components/AffiliateCards'
import TopicCard from '@/components/TopicCard'
import Link from 'next/link'
import {
  getAllCountrySlugs,
  getCountryData,
  getSiteIndex,
  getRelatedCountries,
  getAffiliates,
} from '@/lib/getData'

interface Props {
  params: Promise<{ country: string; topic: string }>
}

export async function generateStaticParams() {
  const slugs = getAllCountrySlugs()
  const params: { country: string; topic: string }[] = []
  for (const countrySlug of slugs) {
    const data = getCountryData(countrySlug)
    if (!data) continue
    for (const topicSlug of Object.keys(data.topics)) {
      params.push({ country: countrySlug, topic: topicSlug })
    }
  }
  return params
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { country: countrySlug, topic: topicSlug } = await params
  const country = getCountryData(countrySlug)
  if (!country) return {}
  const topic = country.topics[topicSlug]
  if (!topic) return {}

  const title = `${topic.title} in ${country.country}: Complete Guide (2025)`
  const description = `${topic.quick_answer} ${topic.tourist_tips[0] ?? ''}`.slice(0, 160)
  const url = `/${countrySlug}/${topicSlug}`

  return {
    title,
    description,
    alternates: { canonical: url },
    openGraph: {
      title,
      description,
      url,
      type: 'article',
      modifiedTime: `${topic.last_verified}-01`,
    },
    twitter: { card: 'summary', title, description },
  }
}

export default async function TopicDetailPage({ params }: Props) {
  const { country: countrySlug, topic: topicSlug } = await params

  const country = getCountryData(countrySlug)
  if (!country) notFound()

  const topic = country.topics[topicSlug]
  if (!topic) notFound()

  const { topics: topicIndex } = getSiteIndex()
  const topicMeta = topicIndex.find((t) => t.slug === topicSlug)

  const relatedCountries: Array<{ country: CountryData; topic: TopicEntry }> =
    getRelatedCountries(countrySlug, country.region, 3)
      .map((c) => ({ country: c, topic: c.topics[topicSlug] }))
      .filter((r): r is { country: CountryData; topic: TopicEntry } => r.topic !== undefined)

  const affiliates = getAffiliates()
  const otherTopics = topicIndex.filter((t) => t.slug !== topicSlug)

  const faqSchema = {
    '@context': 'https://schema.org',
    '@type': 'FAQPage',
    mainEntity: topic.tourist_tips.map((tip, i) => ({
      '@type': 'Question',
      name: `Tip ${i + 1}: ${tip.slice(0, 60)}${tip.length > 60 ? '…' : ''}`,
      acceptedAnswer: { '@type': 'Answer', text: tip },
    })),
  }

  const breadcrumbSchema = {
    '@context': 'https://schema.org',
    '@type': 'BreadcrumbList',
    itemListElement: [
      { '@type': 'ListItem', position: 1, name: 'Home', item: '/' },
      { '@type': 'ListItem', position: 2, name: country.country, item: `/${countrySlug}` },
      { '@type': 'ListItem', position: 3, name: topic.title, item: `/${countrySlug}/${topicSlug}` },
    ],
  }

  return (
    <>
      <script type="application/ld+json" dangerouslySetInnerHTML={{ __html: JSON.stringify(faqSchema) }} />
      <script type="application/ld+json" dangerouslySetInnerHTML={{ __html: JSON.stringify(breadcrumbSchema) }} />

      <Navigation />

      <main className="min-h-screen bg-gray-50">
        {/* Breadcrumb */}
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-3">
            <nav className="flex items-center gap-2 text-sm text-gray-500">
              <Link href="/" className="hover:text-teal-600 transition-colors">Home</Link>
              <span>/</span>
              <Link href={`/${countrySlug}`} className="hover:text-teal-600 transition-colors">
                {country.emoji} {country.country}
              </Link>
              <span>/</span>
              <span className="text-gray-900 font-medium">{topic.title}</span>
            </nav>
          </div>
        </div>

        {/* Hero */}
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-8">
            <div className="flex items-start gap-4">
              <span className="text-5xl">{country.emoji}</span>
              <div>
                <div className="flex items-center gap-2 mb-1">
                  {topicMeta && <span className="text-2xl">{topicMeta.icon}</span>}
                  <span className="text-sm font-medium text-teal-600 uppercase tracking-wide">
                    {topicMeta?.title ?? topic.title}
                  </span>
                </div>
                <h1 className="text-3xl sm:text-4xl font-extrabold text-gray-900 leading-tight">
                  How Does {topicMeta?.title ?? topic.title} Work in {country.country}?
                </h1>
                <p className="mt-2 text-gray-500 text-sm">
                  Last verified: {topic.last_verified} · {country.region}
                </p>
              </div>
            </div>
          </div>
        </div>

        <div className="container-main py-8">
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
            {/* Main content */}
            <div className="lg:col-span-2 space-y-8">
              {/* Section 1 — Quick Answer */}
              <section>
                <h2 className="text-lg font-bold text-gray-900 mb-3 flex items-center gap-2">
                  <span className="w-6 h-6 rounded-full bg-navy-950 text-white text-xs flex items-center justify-center font-bold">1</span>
                  The Quick Answer
                </h2>
                <QuickAnswerBox quickAnswer={topic.quick_answer} warning={topic.warning} />
              </section>

              {/* Section 2 — Full explanation */}
              <section>
                <h2 className="text-lg font-bold text-gray-900 mb-3 flex items-center gap-2">
                  <span className="w-6 h-6 rounded-full bg-navy-950 text-white text-xs flex items-center justify-center font-bold">2</span>
                  What You Need to Know
                </h2>
                <div className="bg-white rounded-2xl border border-gray-100 p-6">
                  <p className="text-gray-700 leading-relaxed text-base">{topic.detail}</p>
                </div>
              </section>

              {/* Section 3 — Tips */}
              <section>
                <h2 className="text-lg font-bold text-gray-900 mb-3 flex items-center gap-2">
                  <span className="w-6 h-6 rounded-full bg-navy-950 text-white text-xs flex items-center justify-center font-bold">3</span>
                  Practical Tips
                </h2>
                <div className="bg-white rounded-2xl border border-gray-100 p-6">
                  <TouristTips tips={topic.tourist_tips} />
                </div>
              </section>

              {/* Section 4 — Warning */}
              {topic.warning && (
                <section>
                  <WarningBox warning={topic.warning} />
                </section>
              )}

              {/* Section 5 — Compare */}
              {relatedCountries.length > 0 && (
                <section>
                  <div className="bg-white rounded-2xl border border-gray-100 p-6">
                    <CountryCompare
                      topicSlug={topicSlug}
                      topicTitle={topicMeta?.title ?? topic.title}
                      relatedCountries={relatedCountries}
                    />
                  </div>
                </section>
              )}
            </div>

            {/* Sidebar */}
            <div className="space-y-6">
              {/* Affiliate cards */}
              {topic.affiliate_relevant.length > 0 && (
                <div className="bg-white rounded-2xl border border-gray-100 p-5">
                  <AffiliateCards
                    affiliateKeys={topic.affiliate_relevant}
                    affiliates={affiliates}
                    countryName={country.country}
                  />
                </div>
              )}

              {/* More topics for this country */}
              <div className="bg-white rounded-2xl border border-gray-100 p-5">
                <h2 className="text-base font-bold text-gray-900 mb-4">
                  More About {country.country}
                </h2>
                <div className="grid gap-2">
                  {otherTopics.slice(0, 6).map((t) => {
                    const entry = country.topics[t.slug]
                    if (!entry) return null
                    return (
                      <TopicCard
                        key={t.slug}
                        topic={t}
                        countrySlug={countrySlug}
                        topicEntry={entry}
                        variant="country"
                      />
                    )
                  })}
                </div>
                <Link
                  href={`/${countrySlug}`}
                  className="block mt-3 text-center text-sm text-teal-600 font-medium hover:text-teal-700 transition-colors"
                >
                  View all topics for {country.country} →
                </Link>
              </div>

              {/* Topic across countries */}
              {topicMeta && (
                <div className="bg-teal-50 rounded-2xl border border-teal-100 p-5">
                  <p className="text-sm font-medium text-teal-800 mb-2">
                    {topicMeta.icon} See {topicMeta.title} rules in all countries
                  </p>
                  <Link
                    href={`/topics/${topicSlug}`}
                    className="inline-block bg-teal-600 text-white text-sm font-semibold px-4 py-2 rounded-lg hover:bg-teal-700 transition-colors"
                  >
                    Compare all countries →
                  </Link>
                </div>
              )}
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
