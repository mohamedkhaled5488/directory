interface Props {
  tips: string[]
}

export default function TouristTips({ tips }: Props) {
  return (
    <div>
      <h2 className="text-xl font-bold text-gray-900 mb-4">Practical Tips</h2>
      <ol className="space-y-3">
        {tips.map((tip, i) => (
          <li key={i} className="flex gap-3">
            <span className="flex-shrink-0 w-7 h-7 rounded-full bg-teal-100 text-teal-700 flex items-center justify-center text-xs font-bold">
              {i + 1}
            </span>
            <span className="text-gray-700 leading-relaxed pt-0.5">{tip}</span>
          </li>
        ))}
      </ol>
    </div>
  )
}
