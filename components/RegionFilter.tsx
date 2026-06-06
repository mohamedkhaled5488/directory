'use client'

import { useState } from 'react'
import Link from 'next/link'
import { CountryIndex, RegionIndex } from '@/lib/types'
import CountryCard from './CountryCard'

interface Props {
  countries: CountryIndex[]
  regions: RegionIndex[]
}

export default function RegionFilter({ countries, regions }: Props) {
  const [active, setActive] = useState('all')

  const filtered = active === 'all'
    ? countries
    : countries.filter((c) => {
        const region = regions.find((r) => r.slug === active)
        return region?.countries.includes(c.slug)
      })

  return (
    <div>
      {/* Tab bar */}
      <div className="flex flex-wrap gap-2 mb-8">
        <button
          onClick={() => setActive('all')}
          className={`px-4 py-2 rounded-full text-sm font-semibold transition-colors ${
            active === 'all'
              ? 'bg-navy-950 text-white'
              : 'bg-white border border-gray-200 text-gray-600 hover:border-teal-300 hover:text-teal-700'
          }`}
        >
          All ({countries.length})
        </button>
        {regions.map((r) => {
          const count = r.countries.length
          return (
            <button
              key={r.slug}
              onClick={() => setActive(r.slug)}
              className={`px-4 py-2 rounded-full text-sm font-semibold transition-colors ${
                active === r.slug
                  ? 'bg-teal-600 text-white'
                  : 'bg-white border border-gray-200 text-gray-600 hover:border-teal-300 hover:text-teal-700'
              }`}
            >
              {r.name} ({count})
            </button>
          )
        })}
      </div>

      {/* Country grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-4">
        {filtered.map((c) => (
          <CountryCard key={c.slug} country={c} />
        ))}
      </div>

      {/* Region browse links */}
      {active === 'all' && (
        <div className="mt-10 pt-8 border-t border-gray-100">
          <p className="text-sm font-semibold text-gray-500 mb-4">Browse by region</p>
          <div className="flex flex-wrap gap-3">
            {regions.map((r) => (
              <Link
                key={r.slug}
                href={`/regions/${r.slug}`}
                className="flex items-center gap-2 px-4 py-2 rounded-xl border border-gray-100 bg-white hover:border-teal-200 hover:bg-teal-50 transition-all text-sm text-gray-700 hover:text-teal-700 font-medium"
              >
                {r.name} →
              </Link>
            ))}
          </div>
        </div>
      )}
    </div>
  )
}
