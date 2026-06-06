import Link from 'next/link'
import { CountryData, TopicEntry } from '@/lib/types'
import { getQuickAnswerSentiment } from '@/lib/getData'

interface Props {
  topicSlug: string
  topicTitle: string
  relatedCountries: Array<{ country: CountryData; topic: TopicEntry }>
}

const sentimentBadge = {
  green:  'bg-emerald-100 text-emerald-700',
  yellow: 'bg-amber-100 text-amber-700',
  red:    'bg-red-100 text-red-700',
}

export default function CountryCompare({ topicSlug, topicTitle, relatedCountries }: Props) {
  if (relatedCountries.length === 0) return null

  return (
    <div>
      <h2 className="text-xl font-bold text-gray-900 mb-4">
        How does this compare?
      </h2>
      <p className="text-sm text-gray-500 mb-4">
        {topicTitle} rules in nearby and similar countries:
      </p>
      <div className="grid gap-3">
        {relatedCountries.map(({ country, topic }) => {
          const sentiment = getQuickAnswerSentiment(topic.warning, topic.quick_answer)
          return (
            <Link
              key={country.slug}
              href={`/${country.slug}/${topicSlug}`}
              className="flex items-start gap-3 p-4 rounded-xl border border-gray-100 bg-white hover:border-teal-200 hover:bg-teal-50 transition-all"
            >
              <span className="text-2xl flex-shrink-0 mt-0.5">{country.emoji}</span>
              <div className="min-w-0 flex-1">
                <div className="flex items-center gap-2 mb-1">
                  <span className="font-semibold text-sm text-gray-900">{country.country}</span>
                  <span className={`text-xs px-2 py-0.5 rounded-full font-medium ${sentimentBadge[sentiment]}`}>
                    {sentiment === 'red' ? 'Warning' : sentiment === 'yellow' ? 'Caution' : 'Normal'}
                  </span>
                </div>
                <p className="text-xs text-gray-500 line-clamp-2 leading-relaxed">
                  {topic.quick_answer}
                </p>
              </div>
              <svg className="w-4 h-4 text-gray-400 flex-shrink-0 mt-1" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5l7 7-7 7" />
              </svg>
            </Link>
          )
        })}
      </div>
    </div>
  )
}
