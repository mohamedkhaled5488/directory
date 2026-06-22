import Link from 'next/link'

export default function Footer() {
  return (
    <footer className="bg-white border-t border-gray-100 mt-12">
      <div className="container-main py-10">
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-8 mb-8">

          {/* Brand */}
          <div>
            <Link href="/" className="flex items-center gap-2 font-bold text-lg text-gray-900 mb-3">
              <span>🌍</span><span>KnowBefore</span>
            </Link>
            <p className="text-sm text-gray-500 leading-relaxed">
              Practical travel knowledge across 50 countries and 25 topics — verified, structured, and free.
            </p>
          </div>

          {/* Explore */}
          <div>
            <p className="font-semibold text-gray-900 text-sm mb-3">Explore</p>
            <ul className="space-y-2">
              <li><Link href="/countries" className="text-sm text-gray-500 hover:text-teal-600 transition-colors">All Countries</Link></li>
              <li><Link href="/topics" className="text-sm text-gray-500 hover:text-teal-600 transition-colors">All Topics</Link></li>
              <li><Link href="/regions/europe" className="text-sm text-gray-500 hover:text-teal-600 transition-colors">Europe</Link></li>
              <li><Link href="/regions/asia" className="text-sm text-gray-500 hover:text-teal-600 transition-colors">Asia</Link></li>
              <li><Link href="/regions/americas" className="text-sm text-gray-500 hover:text-teal-600 transition-colors">Americas</Link></li>
            </ul>
          </div>

          {/* Company */}
          <div>
            <p className="font-semibold text-gray-900 text-sm mb-3">Company</p>
            <ul className="space-y-2">
              <li><Link href="/about" className="text-sm text-gray-500 hover:text-teal-600 transition-colors">About Us</Link></li>
              <li><Link href="/contact" className="text-sm text-gray-500 hover:text-teal-600 transition-colors">Contact</Link></li>
              <li><Link href="/privacy-policy" className="text-sm text-gray-500 hover:text-teal-600 transition-colors">Privacy Policy</Link></li>
            </ul>
          </div>

        </div>

        <div className="border-t border-gray-100 pt-6 flex flex-col sm:flex-row items-center justify-between gap-3">
          <p className="text-xs text-gray-400">
            © {new Date().getFullYear()} KnowBefore. For informational purposes only — always verify critical travel information with official sources.
          </p>
          <div className="flex gap-4">
            <Link href="/privacy-policy" className="text-xs text-gray-400 hover:text-teal-600 transition-colors">Privacy</Link>
            <Link href="/contact" className="text-xs text-gray-400 hover:text-teal-600 transition-colors">Contact</Link>
            <Link href="/about" className="text-xs text-gray-400 hover:text-teal-600 transition-colors">About</Link>
          </div>
        </div>
      </div>
    </footer>
  )
}
