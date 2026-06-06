'use client'

import { useState, useEffect, useRef } from 'react'
import Link from 'next/link'
import { LightSearchResult } from '@/lib/types'

interface Props {
  searchIndex: LightSearchResult[]
}

function highlight(text: string, query: string): React.ReactNode {
  if (!query.trim()) return text
  const regex = new RegExp(`(${query.replace(/[.*+?^${}()|[\]\\]/g, '\\$&')})`, 'gi')
  const parts = text.split(regex)
  return parts.map((part, i) =>
    regex.test(part) ? (
      <mark key={i} className="bg-yellow-200 text-yellow-900 rounded px-0.5">
        {part}
      </mark>
    ) : part
  )
}

export default function SearchBar({ searchIndex }: Props) {
  const [query, setQuery] = useState('')
  const [results, setResults] = useState<LightSearchResult[]>([])
  const [open, setOpen] = useState(false)
  const ref = useRef<HTMLDivElement>(null)

  useEffect(() => {
    const q = query.trim().toLowerCase()
    if (q.length < 2) {
      setResults([])
      setOpen(false)
      return
    }
    const matched = searchIndex
      .filter(
        (r) =>
          r.countryName.toLowerCase().includes(q) ||
          r.topicTitle.toLowerCase().includes(q) ||
          r.quickAnswer.toLowerCase().includes(q)
      )
      .slice(0, 8)
    setResults(matched)
    setOpen(matched.length > 0)
  }, [query, searchIndex])

  useEffect(() => {
    function handleClick(e: MouseEvent) {
      if (ref.current && !ref.current.contains(e.target as Node)) {
        setOpen(false)
      }
    }
    document.addEventListener('mousedown', handleClick)
    return () => document.removeEventListener('mousedown', handleClick)
  }, [])

  return (
    <div ref={ref} className="relative w-full max-w-2xl mx-auto">
      <div className="relative">
        <svg
          className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400"
          fill="none" viewBox="0 0 24 24" stroke="currentColor"
        >
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2}
            d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
        </svg>
        <input
          type="text"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          onFocus={() => results.length > 0 && setOpen(true)}
          placeholder="Search any country or topic… e.g. tipping in Japan"
          className="w-full pl-12 pr-4 py-4 rounded-2xl border-2 border-gray-200 focus:border-teal-400 focus:outline-none text-gray-900 placeholder-gray-400 text-base shadow-sm bg-white"
          autoComplete="off"
        />
        {query && (
          <button
            onClick={() => { setQuery(''); setOpen(false) }}
            className="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600"
          >
            ✕
          </button>
        )}
      </div>

      {open && (
        <div className="absolute top-full left-0 right-0 mt-2 bg-white rounded-2xl border border-gray-200 shadow-xl z-50 overflow-hidden">
          {results.map((r, i) => (
            <Link
              key={i}
              href={r.url}
              onClick={() => { setOpen(false); setQuery('') }}
              className="flex items-start gap-3 px-4 py-3 hover:bg-gray-50 border-b border-gray-50 last:border-0 transition-colors"
            >
              <span className="text-2xl flex-shrink-0 mt-0.5">{r.countryEmoji}</span>
              <div className="min-w-0 flex-1">
                <p className="font-semibold text-sm text-gray-900">
                  {highlight(r.topicTitle, query)} in {highlight(r.countryName, query)}
                </p>
                <p className="text-xs text-gray-500 mt-0.5 line-clamp-1">
                  {highlight(r.quickAnswer, query)}
                </p>
              </div>
              <span className="text-xs text-teal-600 font-medium flex-shrink-0 mt-1">→</span>
            </Link>
          ))}
        </div>
      )}
    </div>
  )
}
