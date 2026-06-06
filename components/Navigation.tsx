'use client'

import Link from 'next/link'
import { useState } from 'react'

export default function Navigation() {
  const [menuOpen, setMenuOpen] = useState(false)

  return (
    <nav className="bg-white border-b border-gray-100 sticky top-0 z-50 shadow-sm">
      <div className="container-main">
        <div className="flex items-center justify-between h-16">
          <Link href="/" className="flex items-center gap-2 font-bold text-xl text-navy-950 hover:text-teal-600 transition-colors">
            <span className="text-2xl">🌍</span>
            <span>KnowBefore</span>
          </Link>

          <div className="hidden md:flex items-center gap-6">
            <Link href="/countries" className="text-gray-600 hover:text-navy-950 font-medium transition-colors text-sm">
              Countries
            </Link>
            <Link href="/topics" className="text-gray-600 hover:text-navy-950 font-medium transition-colors text-sm">
              Topics
            </Link>
            <Link
              href="/countries"
              className="bg-teal-600 text-white px-4 py-2 rounded-lg text-sm font-medium hover:bg-teal-700 transition-colors"
            >
              Browse All
            </Link>
          </div>

          <button
            className="md:hidden p-2 rounded-lg text-gray-600 hover:bg-gray-100"
            onClick={() => setMenuOpen(!menuOpen)}
            aria-label="Toggle menu"
          >
            {menuOpen ? (
              <svg className="w-5 h-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
              </svg>
            ) : (
              <svg className="w-5 h-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
              </svg>
            )}
          </button>
        </div>

        {menuOpen && (
          <div className="md:hidden border-t border-gray-100 py-3 space-y-1">
            <Link
              href="/countries"
              className="block px-3 py-2 text-gray-700 hover:bg-gray-50 rounded-lg font-medium"
              onClick={() => setMenuOpen(false)}
            >
              Countries
            </Link>
            <Link
              href="/topics"
              className="block px-3 py-2 text-gray-700 hover:bg-gray-50 rounded-lg font-medium"
              onClick={() => setMenuOpen(false)}
            >
              Topics
            </Link>
          </div>
        )}
      </div>
    </nav>
  )
}
