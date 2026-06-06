interface Props {
  warning: string
}

export default function WarningBox({ warning }: Props) {
  return (
    <div className="rounded-xl border-2 border-red-200 bg-red-50 p-5">
      <div className="flex gap-3">
        <span className="text-2xl flex-shrink-0">⛔</span>
        <div>
          <p className="font-bold text-red-800 mb-1 text-sm uppercase tracking-wide">
            Important Warning
          </p>
          <p className="text-red-900 leading-relaxed">{warning}</p>
        </div>
      </div>
    </div>
  )
}
