import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'
import CountryCard from '@/components/CountryCard'
import TopicCard from '@/components/TopicCard'
import SearchBar from '@/components/SearchBar'
import { getSiteIndex, buildLightSearchIndex } from '@/lib/getData'

export const metadata: Metadata = {
  title: 'WorldHow — How Things Work in Every Country',
  description: 'Quick answers to everyday travel questions by country. Tipping, transport, healthcare, local laws — verified local knowledge for tourists.',
  alternates: { canonical: '/' },
  openGraph: {
    title: 'WorldHow — How Things Work in Every Country',
    description: 'Quick answers to everyday travel questions by country.',
    url: '/',
    type: 'website',
  },
}

const MOST_SEARCHED = [
  { label: 'Tipping in Japan', href: '/japan/tipping', emoji: '🇯🇵' },
  { label: 'Alcohol rules in UAE', href: '/uae/alcohol-rules', emoji: '🇦🇪' },
  { label: 'Local laws in Thailand', href: '/thailand/local-laws', emoji: '🇹🇭' },
  { label: 'Transport in UK', href: '/uk/public-transport', emoji: '🇬🇧' },
  { label: 'Bargaining in Turkey', href: '/turkey/bargaining-culture', emoji: '🇹🇷' },
  { label: 'Dress code in Italy', href: '/italy/dress-code', emoji: '🇮🇹' },
]

export default function HomePage() {
  const { countries, topics } = getSiteIndex()
  const searchIndex = buildLightSearchIndex()

  return (
    <>
      <Navigation />

      <main>
        {/* Hero */}
        <section className="bg-gradient-to-br from-navy-950 via-navy-900 to-teal-900 text-white">
          <div className="container-main py-16 sm:py-24">
            <div className="max-w-3xl mx-auto text-center">
              <div className="inline-flex items-center gap-2 bg-white/10 rounded-full px-4 py-2 text-sm font-medium mb-6 text-teal-200">
                <span>✅</span>
                Verified local knowledge for 1,250+ situations
              </div>
              <h1 className="text-4xl sm:text-5xl lg:text-6xl font-extrabold leading-tight mb-6">
                Understand how{' '}
                <span className="text-teal-400">everyday things work</span>{' '}
                in any country — instantly
              </h1>
              <p className="text-lg sm:text-xl text-gray-300 mb-10 max-w-2xl mx-auto">
                Tipping customs, transport, local laws, healthcare, dress codes and more.
                Fast answers for tourists, not long blog posts.
              </p>

              <SearchBar searchIndex={searchIndex} />

              <div className="mt-6 flex flex-wrap justify-center gap-2">
                {MOST_SEARCHED.map((item) => (
                  <Link
                    key={item.href}
                    href={item.href}
                    className="flex items-center gap-1.5 bg-white/10 hover:bg-white/20 text-white text-xs px-3 py-1.5 rounded-full transition-colors"
                  >
                    <span>{item.emoji}</span>
                    <span>{item.label}</span>
                  </Link>
                ))}
              </div>
            </div>
          </div>
        </section>

        {/* Countries grid */}
        <section className="bg-gray-50 py-16">
          <div className="container-main">
            <div className="flex items-center justify-between mb-8">
              <div>
                <h2 className="text-2xl sm:text-3xl font-extrabold text-gray-900">Browse by Country</h2>
                <p className="text-gray-500 mt-1">50 countries with 25 topics each — 1,250 guides total</p>
              </div>
              <Link
                href="/countries"
                className="hidden sm:inline-flex items-center gap-1 text-teal-600 font-semibold text-sm hover:text-teal-700 transition-colors"
              >
                View all <span>→</span>
              </Link>
            </div>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-4">
              {countries.map((c) => (
                <CountryCard key={c.slug} country={c} />
              ))}
            </div>
            <div className="mt-6 sm:hidden text-center">
              <Link href="/countries" className="text-teal-600 font-semibold text-sm">View all countries →</Link>
            </div>
          </div>
        </section>

        {/* Topics grid */}
        <section className="bg-white py-16">
          <div className="container-main">
            <div className="flex items-center justify-between mb-8">
              <div>
                <h2 className="text-2xl sm:text-3xl font-extrabold text-gray-900">Browse by Topic</h2>
                <p className="text-gray-500 mt-1">Compare a topic across all 50 countries at once</p>
              </div>
              <Link
                href="/topics"
                className="hidden sm:inline-flex items-center gap-1 text-teal-600 font-semibold text-sm hover:text-teal-700 transition-colors"
              >
                View all <span>→</span>
              </Link>
            </div>
            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-5 gap-4">
              {topics.map((t) => (
                <TopicCard key={t.slug} topic={t} />
              ))}
            </div>
          </div>
        </section>

        {/* Most searched */}
        <section className="bg-teal-50 py-14">
          <div className="container-main">
            <h2 className="text-2xl font-extrabold text-gray-900 mb-2 text-center">Most Searched Guides</h2>
            <p className="text-gray-500 text-center mb-8">Start with the questions travelers ask most</p>
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
              {MOST_SEARCHED.map((item) => (
                <Link
                  key={item.href}
                  href={item.href}
                  className="flex items-center gap-3 bg-white rounded-xl border border-teal-100 px-5 py-4 hover:border-teal-300 hover:shadow-sm transition-all group"
                >
                  <span className="text-3xl">{item.emoji}</span>
                  <span className="font-semibold text-gray-900 group-hover:text-teal-700 transition-colors text-sm">
                    {item.label}
                  </span>
                  <span className="ml-auto text-teal-600 text-sm">→</span>
                </Link>
              ))}
            </div>
          </div>
        </section>

        {/* Trust strip */}
        <section className="bg-white py-12 border-t border-gray-100">
          <div className="container-main">
            <div className="grid grid-cols-1 sm:grid-cols-3 gap-6 text-center">
              <div className="flex flex-col items-center gap-2">
                <span className="text-3xl">📋</span>
                <p className="font-bold text-gray-900">1,250+ Verified Guides</p>
                <p className="text-sm text-gray-500">50 countries × 25 topics, with accurate local knowledge</p>
              </div>
              <div className="flex flex-col items-center gap-2">
                <span className="text-3xl">⚡</span>
                <p className="font-bold text-gray-900">Instant Answers</p>
                <p className="text-sm text-gray-500">Quick-answer format, not long blog posts</p>
              </div>
              <div className="flex flex-col items-center gap-2">
                <span className="text-3xl">🔄</span>
                <p className="font-bold text-gray-900">Regularly Verified</p>
                <p className="text-sm text-gray-500">Last-verified dates shown on every guide</p>
              </div>
            </div>
          </div>
        </section>
      </main>

      <footer className="bg-navy-950 text-white">
        <div className="container-main py-10">
          <div className="flex flex-col sm:flex-row items-start sm:items-center justify-between gap-6">
            <div>
              <div className="flex items-center gap-2 font-bold text-xl mb-1">
                <span>🌍</span><span>WorldHow</span>
              </div>
              <p className="text-gray-400 text-sm">Practical travel knowledge for every country.</p>
            </div>
            <div className="flex gap-6 text-sm text-gray-400">
              <Link href="/countries" className="hover:text-white transition-colors">Countries</Link>
              <Link href="/topics" className="hover:text-white transition-colors">Topics</Link>
            </div>
          </div>
        </div>
      </footer>
    </>
  )
}
