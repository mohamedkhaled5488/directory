import { AffiliateMap } from '@/lib/types'

interface Props {
  affiliateKeys: string[]
  affiliates: AffiliateMap
  countryName: string
}

export default function AffiliateCards({ affiliateKeys, affiliates, countryName }: Props) {
  const items = affiliateKeys
    .map((key) => ({ key, ...affiliates[key] }))
    .filter(Boolean)

  if (items.length === 0) return null

  return (
    <div>
      <h2 className="text-xl font-bold text-gray-900 mb-2">
        Traveling to {countryName}?
      </h2>
      <p className="text-sm text-gray-500 mb-4">You might also need:</p>
      <div className="grid gap-3">
        {items.map((item) => (
          <a
            key={item.key}
            href={item.url}
            target="_blank"
            rel="noopener noreferrer sponsored"
            className="flex items-center gap-4 p-4 rounded-xl border border-gray-100 bg-white hover:border-teal-200 hover:shadow-sm transition-all group"
          >
            <span className="text-3xl flex-shrink-0">{item.icon}</span>
            <div className="flex-1 min-w-0">
              <p className="font-semibold text-sm text-gray-900 group-hover:text-teal-700 transition-colors">
                {item.name}
              </p>
              <p className="text-xs text-gray-500 mt-0.5 leading-relaxed">
                {item.description}
              </p>
            </div>
            <div className="flex-shrink-0">
              <span className="inline-block bg-teal-600 text-white text-xs font-semibold px-3 py-1.5 rounded-lg group-hover:bg-teal-700 transition-colors whitespace-nowrap">
                {item.cta}
              </span>
            </div>
          </a>
        ))}
      </div>
    </div>
  )
}
