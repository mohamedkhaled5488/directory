'use client'

import Link from 'next/link'

export default function Error({
  error,
  reset,
}: {
  error: Error & { digest?: string }
  reset: () => void
}) {
  return (
    <main className="min-h-screen bg-gray-50 flex flex-col items-center justify-center px-4">
      <div className="text-center max-w-md">
        <p className="text-6xl mb-4">⚠️</p>
        <h2 className="text-2xl font-bold text-gray-900 mb-2">Something went wrong</h2>
        <p className="text-gray-500 mb-6 text-sm">{error.message || 'An unexpected error occurred.'}</p>
        <div className="flex flex-wrap justify-center gap-3">
          <button
            onClick={reset}
            className="bg-teal-600 text-white px-5 py-2 rounded-lg font-semibold hover:bg-teal-700 transition-colors text-sm"
          >
            Try again
          </button>
          <Link
            href="/"
            className="border border-gray-200 text-gray-700 px-5 py-2 rounded-lg font-semibold hover:bg-gray-50 transition-colors text-sm"
          >
            Go home
          </Link>
        </div>
      </div>
    </main>
  )
}
