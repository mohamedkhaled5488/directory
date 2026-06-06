import { notFound } from 'next/navigation'
import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'
import CountryCard from '@/components/CountryCard'
import { getSiteIndex, getCountriesByRegion } from '@/lib/getData'
import { RegionIndex } from '@/lib/types'

interface Props {
  params: Promise<{ region: string }>
}

export async function generateStaticParams() {
  const { regions } = getSiteIndex() as ReturnType<typeof getSiteIndex> & { regions: RegionIndex[] }
  return (regions ?? []).map((r) => ({ region: r.slug }))
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { region: regionSlug } = await params
  const { regions } = getSiteIndex() as ReturnType<typeof getSiteIndex> & { regions: RegionIndex[] }
  const region = (regions ?? []).find((r) => r.slug === regionSlug)
  if (!region) return {}

  const title = `How Things Work in ${region.name} — Travel Guides`
  const description = `Complete travel guides for ${region.countries.length} countries in ${region.name}. Tipping, transport, local laws, healthcare, dress codes and more.`
  return {
    title,
    description,
    alternates: { canonical: `/regions/${regionSlug}` },
    openGraph: { title, description, url: `/regions/${regionSlug}`, type: 'website' },
  }
}

export default async function RegionPage({ params }: Props) {
  const { region: regionSlug } = await params
  const { regions } = getSiteIndex() as ReturnType<typeof getSiteIndex> & { regions: RegionIndex[] }
  const region = (regions ?? []).find((r) => r.slug === regionSlug)
  if (!region) notFound()

  const countries = getCountriesByRegion(regionSlug)

  return (
    <>
      <Navigation />
      <main className="min-h-screen bg-gray-50">
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-3">
            <nav className="flex items-center gap-2 text-sm text-gray-500">
              <Link href="/" className="hover:text-teal-600">Home</Link>
              <span>/</span>
              <Link href="/countries" className="hover:text-teal-600">Countries</Link>
              <span>/</span>
              <span className="text-gray-900 font-medium">{region.name}</span>
            </nav>
          </div>
        </div>

        <div className="bg-gradient-to-br from-navy-950 to-teal-900 text-white">
          <div className="container-main py-12">
            <h1 className="text-4xl font-extrabold mb-2">How Things Work in {region.name}</h1>
            <p className="text-teal-200 text-lg">
              {countries.length} countries · practical guides for every destination
            </p>
          </div>
        </div>

        <div className="container-main py-8">
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
            {countries.map((c) => (
              <CountryCard key={c.slug} country={{
                name: c.country,
                slug: c.slug,
                emoji: c.emoji,
                region: c.region,
                topicCount: Object.keys(c.topics).length,
                mostSearched: [],
              }} />
            ))}
          </div>

          {/* Browse other regions */}
          <div className="mt-12 pt-8 border-t border-gray-100">
            <h2 className="text-lg font-bold text-gray-900 mb-4">Other Regions</h2>
            <div className="flex flex-wrap gap-3">
              {(regions ?? []).filter((r) => r.slug !== regionSlug).map((r) => (
                <Link
                  key={r.slug}
                  href={`/regions/${r.slug}`}
                  className="px-4 py-2 rounded-xl border border-gray-100 bg-white hover:border-teal-200 hover:bg-teal-50 transition-all text-sm font-medium text-gray-700 hover:text-teal-700"
                >
                  {r.name} ({r.countries.length})
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
