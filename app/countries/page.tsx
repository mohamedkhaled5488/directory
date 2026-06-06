import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'
import RegionFilter from '@/components/RegionFilter'
import { getSiteIndex } from '@/lib/getData'
import { RegionIndex } from '@/lib/types'

export const metadata: Metadata = {
  title: 'All Countries — KnowBefore',
  description: 'Browse travel guides for 50 countries. Tipping, local laws, transport, healthcare and more for Japan, USA, Thailand, Europe, Asia, Americas and beyond.',
  alternates: { canonical: '/countries' },
}

export default function CountriesPage() {
  const { countries, regions } = getSiteIndex() as ReturnType<typeof getSiteIndex> & { regions: RegionIndex[] }

  return (
    <>
      <Navigation />
      <main className="min-h-screen bg-gray-50">
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-3">
            <nav className="flex items-center gap-2 text-sm text-gray-500">
              <Link href="/" className="hover:text-teal-600 transition-colors">Home</Link>
              <span>/</span>
              <span className="text-gray-900 font-medium">Countries</span>
            </nav>
          </div>
        </div>

        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-10">
            <h1 className="text-4xl font-extrabold text-gray-900 mb-2">All Countries</h1>
            <p className="text-gray-500">
              {countries.length} countries · {countries.reduce((s, c) => s + c.topicCount, 0).toLocaleString()} guides
            </p>
          </div>
        </div>

        <div className="container-main py-8">
          <RegionFilter countries={countries} regions={regions ?? []} />
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
