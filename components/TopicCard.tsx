import Link from 'next/link'
import { TopicIndex, TopicEntry } from '@/lib/types'
import { getQuickAnswerSentiment } from '@/lib/getData'

interface Props {
  topic: TopicIndex
  countrySlug?: string
  topicEntry?: TopicEntry
  variant?: 'default' | 'country'
}

const sentimentClasses = {
  green:  { badge: 'badge-green',  dot: 'bg-emerald-400' },
  yellow: { badge: 'badge-yellow', dot: 'bg-amber-400'   },
  red:    { badge: 'badge-red',    dot: 'bg-red-400'     },
}

export default function TopicCard({ topic, countrySlug, topicEntry, variant = 'default' }: Props) {
  const href = countrySlug ? `/${countrySlug}/${topic.slug}` : `/topics/${topic.slug}`

  if (variant === 'country' && topicEntry) {
    const sentiment = getQuickAnswerSentiment(topicEntry.warning, topicEntry.quick_answer)
    const sc = sentimentClasses[sentiment]

    return (
      <Link href={href} className="group flex flex-col p-4 rounded-xl border border-gray-100 bg-white card-hover">
        <div className="flex items-start justify-between mb-2">
          <div className="flex items-center gap-2">
            <span className="text-xl">{topic.icon}</span>
            <span className="font-semibold text-sm text-gray-900 group-hover:text-teal-700 transition-colors">
              {topic.title}
            </span>
            {topic.hot && (
              <span className="text-xs" title="Most searched topic">🔥</span>
            )}
          </div>
          <span className={sc.badge}>
            <span className={`w-1.5 h-1.5 rounded-full ${sc.dot} mr-1 inline-block`} />
            {sentiment === 'red' ? 'Warning' : sentiment === 'yellow' ? 'Caution' : 'Normal'}
          </span>
        </div>
        <p className="text-xs text-gray-500 line-clamp-2 leading-relaxed">{topicEntry.quick_answer}</p>
        <p className="mt-2 text-xs text-gray-400">Updated {topicEntry.last_verified}</p>
      </Link>
    )
  }

  return (
    <Link href={href} className="group relative flex flex-col items-center p-5 rounded-2xl border border-gray-100 bg-white card-hover text-center">
      {topic.hot && (
        <span className="absolute top-2 right-2 text-sm" title="Most searched">🔥</span>
      )}
      <span className="text-4xl mb-3">{topic.icon}</span>
      <h3 className="font-bold text-sm text-gray-900 group-hover:text-teal-700 transition-colors mb-1">
        {topic.title}
      </h3>
      <p className="text-xs text-gray-500 leading-relaxed">{topic.description}</p>
    </Link>
  )
}
