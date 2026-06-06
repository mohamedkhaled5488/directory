import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'
import { getSiteIndex } from '@/lib/getData'

export const metadata: Metadata = {
  title: 'Page Not Found — KnowBefore',
}

export default function NotFound() {
  const { countries, topics } = getSiteIndex()

  return (
    <>
      <Navigation />
      <main className="min-h-screen bg-gray-50 flex flex-col items-center justify-center px-4">
        <div className="text-center max-w-lg">
          <p className="text-7xl mb-4">🌍</p>
          <h1 className="text-4xl font-extrabold text-gray-900 mb-3">Page not found</h1>
          <p className="text-gray-500 mb-8">
            We couldn't find that country or topic. Browse what we have below.
          </p>
          <div className="flex flex-wrap justify-center gap-3 mb-8">
            <Link
              href="/"
              className="bg-teal-600 text-white px-5 py-2.5 rounded-lg font-semibold hover:bg-teal-700 transition-colors text-sm"
            >
              Back to home
            </Link>
            <Link
              href="/countries"
              className="border border-gray-200 text-gray-700 px-5 py-2.5 rounded-lg font-semibold hover:bg-gray-50 transition-colors text-sm"
            >
              Browse countries
            </Link>
          </div>

          <div className="text-left bg-white rounded-2xl border border-gray-100 p-5">
            <p className="text-xs font-bold uppercase text-gray-400 tracking-wider mb-3">Quick links</p>
            <div className="grid grid-cols-2 gap-2">
              {countries.slice(0, 4).map((c) => (
                <Link
                  key={c.slug}
                  href={`/${c.slug}`}
                  className="flex items-center gap-2 text-sm text-gray-700 hover:text-teal-600 py-1 transition-colors"
                >
                  <span>{c.emoji}</span>
                  <span>{c.name}</span>
                </Link>
              ))}
              {topics.slice(0, 4).map((t) => (
                <Link
                  key={t.slug}
                  href={`/topics/${t.slug}`}
                  className="flex items-center gap-2 text-sm text-gray-700 hover:text-teal-600 py-1 transition-colors"
                >
                  <span>{t.icon}</span>
                  <span>{t.title}</span>
                </Link>
              ))}
            </div>
          </div>
        </div>
      </main>
    </>
  )
}
