import Link from 'next/link'
import { CountryIndex } from '@/lib/types'

interface Props {
  country: CountryIndex
  variant?: 'default' | 'compact'
}

export default function CountryCard({ country, variant = 'default' }: Props) {
  if (variant === 'compact') {
    return (
      <Link
        href={`/${country.slug}`}
        className="flex items-center gap-3 p-3 rounded-xl border border-gray-100 bg-white card-hover"
      >
        <span className="text-3xl">{country.emoji}</span>
        <div className="min-w-0">
          <p className="font-semibold text-gray-900 text-sm truncate">{country.name}</p>
          <p className="text-xs text-gray-500">{country.region}</p>
        </div>
      </Link>
    )
  }

  return (
    <Link
      href={`/${country.slug}`}
      className="group flex flex-col p-5 rounded-2xl border border-gray-100 bg-white card-hover"
    >
      <div className="flex items-start justify-between mb-3">
        <span className="text-5xl">{country.emoji}</span>
        <span className="text-xs text-gray-400 bg-gray-50 px-2 py-1 rounded-full">
          {country.region}
        </span>
      </div>
      <h3 className="font-bold text-lg text-gray-900 group-hover:text-teal-700 transition-colors mb-1">
        {country.name}
      </h3>
      <p className="text-sm text-gray-500 mb-3">
        {country.topicCount} topics covered
      </p>
      <div className="flex flex-wrap gap-1 mt-auto">
        {country.mostSearched.map((slug) => (
          <span key={slug} className="text-xs bg-teal-50 text-teal-700 px-2 py-0.5 rounded-full capitalize">
            {slug.replace(/-/g, ' ')}
          </span>
        ))}
      </div>
    </Link>
  )
}
