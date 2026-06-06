import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'
import TopicCard from '@/components/TopicCard'
import { getSiteIndex } from '@/lib/getData'

export const metadata: Metadata = {
  title: 'All Topics — KnowBefore',
  description: 'Browse every travel topic compared across countries. Tipping, local laws, transport, healthcare, dress codes, alcohol rules and more.',
  alternates: { canonical: '/topics' },
}

export default function TopicsPage() {
  const { topics, countries } = getSiteIndex()

  return (
    <>
      <Navigation />

      <main className="min-h-screen bg-gray-50">
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-3">
            <nav className="flex items-center gap-2 text-sm text-gray-500">
              <Link href="/" className="hover:text-teal-600 transition-colors">Home</Link>
              <span>/</span>
              <span className="text-gray-900 font-medium">Topics</span>
            </nav>
          </div>
        </div>

        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-10">
            <h1 className="text-4xl font-extrabold text-gray-900 mb-2">All Topics</h1>
            <p className="text-gray-500">
              Each topic is compared across all {countries.length} countries on KnowBefore
            </p>
          </div>
        </div>

        <div className="container-main py-8">
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-5">
            {topics.map((t) => (
              <div key={t.slug} className="flex flex-col">
                <TopicCard topic={t} />
                <div className="mt-2 flex flex-wrap gap-1 px-1">
                  {countries.slice(0, 3).map((c) => (
                    <Link
                      key={c.slug}
                      href={`/${c.slug}/${t.slug}`}
                      className="text-xs text-gray-500 hover:text-teal-600 transition-colors"
                    >
                      {c.emoji} {c.name}
                    </Link>
                  ))}
                  <span className="text-xs text-gray-400">+{countries.length - 3} more</span>
                </div>
              </div>
            ))}
          </div>
        </div>
      </main>

      <footer className="bg-white border-t border-gray-100">
        <div className="container-main py-8">
          <div className="flex flex-col sm:flex-row items-center justify-between gap-4">
            <Link href="/" className="flex items-center gap-2 font-bold text-lg text-navy-950">
              <span>🌍</span><span>KnowBefore</span>
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
