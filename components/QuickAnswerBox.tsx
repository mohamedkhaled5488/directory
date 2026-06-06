import { getQuickAnswerSentiment } from '@/lib/getData'

interface Props {
  quickAnswer: string
  warning: string | null
}

const config = {
  green: {
    wrapper: 'bg-emerald-50 border-emerald-200',
    icon: '✅',
    label: 'Quick Answer',
    labelClass: 'text-emerald-700',
    textClass: 'text-emerald-900',
  },
  yellow: {
    wrapper: 'bg-amber-50 border-amber-200',
    icon: '⚠️',
    label: 'Important',
    labelClass: 'text-amber-700',
    textClass: 'text-amber-900',
  },
  red: {
    wrapper: 'bg-red-50 border-red-200',
    icon: '🚨',
    label: 'Warning',
    labelClass: 'text-red-700',
    textClass: 'text-red-900',
  },
}

export default function QuickAnswerBox({ quickAnswer, warning }: Props) {
  const sentiment = getQuickAnswerSentiment(warning, quickAnswer)
  const c = config[sentiment]

  return (
    <div className={`rounded-2xl border-2 p-6 ${c.wrapper}`}>
      <div className="flex items-center gap-2 mb-3">
        <span className="text-xl">{c.icon}</span>
        <span className={`text-xs font-bold uppercase tracking-wider ${c.labelClass}`}>
          {c.label}
        </span>
      </div>
      <p className={`text-lg font-bold leading-snug ${c.textClass}`}>
        {quickAnswer}
      </p>
    </div>
  )
}
