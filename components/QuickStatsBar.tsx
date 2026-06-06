import { CountryStats } from '@/lib/types'

interface Props {
  stats: CountryStats
}

const statItems = [
  { key: 'timezone' as const,  icon: '🕐', label: 'Timezone' },
  { key: 'currency' as const,  icon: '💰', label: 'Currency' },
  { key: 'language' as const,  icon: '🗣️', label: 'Language' },
  { key: 'plugType' as const,  icon: '🔌', label: 'Plug' },
  { key: 'bestTime' as const,  icon: '🌤️', label: 'Best time' },
]

export default function QuickStatsBar({ stats }: Props) {
  return (
    <div className="flex flex-wrap gap-2 mt-4">
      {statItems.map(({ key, icon, label }) => (
        <div
          key={key}
          className="flex items-center gap-1.5 bg-white border border-gray-200 rounded-full px-3 py-1.5 text-xs"
          title={label}
        >
          <span>{icon}</span>
          <span className="text-gray-500 font-medium">{label}:</span>
          <span className="text-gray-900 font-semibold max-w-[120px] truncate">{stats[key]}</span>
        </div>
      ))}
    </div>
  )
}
