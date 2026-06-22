import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'

export const metadata: Metadata = {
  title: 'Contact Us — KnowBefore',
  description: 'Get in touch with the KnowBefore team. Report outdated information, suggest new countries or topics, or ask a question about our travel guides.',
  alternates: { canonical: '/contact' },
}

export default function ContactPage() {
  return (
    <>
      <Navigation />
      <main className="min-h-screen bg-gray-50">
        {/* Breadcrumb */}
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-3">
            <nav className="flex items-center gap-2 text-sm text-gray-500">
              <Link href="/" className="hover:text-teal-600 transition-colors">Home</Link>
              <span>/</span>
              <span className="text-gray-900 font-medium">Contact</span>
            </nav>
          </div>
        </div>

        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-12">
            <h1 className="text-4xl font-extrabold text-gray-900 mb-3">Contact Us</h1>
            <p className="text-gray-600 text-lg">
              We read every message and aim to respond within 48 hours.
            </p>
          </div>
        </div>

        <div className="container-main py-12">
          <div className="max-w-3xl">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-12">

              {/* Reason cards */}
              {[
                {
                  emoji: '📋',
                  title: 'Report outdated information',
                  desc: 'Laws and local customs change. If you spot information that is no longer accurate, let us know and we\'ll review and update it.',
                },
                {
                  emoji: '💡',
                  title: 'Suggest a new country or topic',
                  desc: 'We cover 50 countries and 25 topics. If you\'d like to see a destination or subject added, we\'d love to hear it.',
                },
                {
                  emoji: '🤝',
                  title: 'Partnership & press enquiries',
                  desc: 'Interested in featuring KnowBefore or collaborating? Reach out and we\'ll get back to you promptly.',
                },
                {
                  emoji: '🔒',
                  title: 'Privacy & data requests',
                  desc: 'For privacy-related requests including data deletion or access requests, please contact us directly.',
                },
              ].map((card) => (
                <div key={card.title} className="bg-white rounded-2xl border border-gray-100 p-6">
                  <div className="text-3xl mb-3">{card.emoji}</div>
                  <h2 className="font-bold text-gray-900 mb-2">{card.title}</h2>
                  <p className="text-gray-600 text-sm leading-relaxed">{card.desc}</p>
                </div>
              ))}
            </div>

            {/* Contact info */}
            <div className="bg-white rounded-2xl border border-gray-100 p-8 mb-8">
              <h2 className="text-xl font-bold text-gray-900 mb-6">How to Reach Us</h2>

              <div className="space-y-5">
                <div className="flex items-start gap-4">
                  <div className="flex-shrink-0 w-10 h-10 rounded-xl bg-teal-50 flex items-center justify-center text-lg">
                    ✉️
                  </div>
                  <div>
                    <p className="font-semibold text-gray-900 mb-0.5">Email</p>
                    <p className="text-gray-600 text-sm">
                      Send us an email at{' '}
                      <a
                        href="mailto:hello@theknowbefore.com"
                        className="text-teal-600 hover:underline font-medium"
                      >
                        hello@theknowbefore.com
                      </a>
                    </p>
                    <p className="text-gray-400 text-xs mt-1">We aim to reply within 48 hours on business days.</p>
                  </div>
                </div>

                <div className="flex items-start gap-4">
                  <div className="flex-shrink-0 w-10 h-10 rounded-xl bg-teal-50 flex items-center justify-center text-lg">
                    🌐
                  </div>
                  <div>
                    <p className="font-semibold text-gray-900 mb-0.5">Website</p>
                    <p className="text-gray-600 text-sm">theknowbefore.com</p>
                  </div>
                </div>

                <div className="flex items-start gap-4">
                  <div className="flex-shrink-0 w-10 h-10 rounded-xl bg-teal-50 flex items-center justify-center text-lg">
                    📍
                  </div>
                  <div>
                    <p className="font-semibold text-gray-900 mb-0.5">About the Team</p>
                    <p className="text-gray-600 text-sm">
                      KnowBefore is an independent travel information platform. Our research team sources
                      information from official government and embassy publications across all 50 countries
                      we cover.
                    </p>
                  </div>
                </div>
              </div>
            </div>

            {/* Quick links */}
            <div className="bg-teal-50 rounded-2xl border border-teal-100 p-6">
              <h2 className="font-bold text-gray-900 mb-3">Before you write — quick links</h2>
              <p className="text-gray-600 text-sm mb-4">
                Your answer might already be on the site:
              </p>
              <div className="flex flex-wrap gap-3">
                <Link href="/countries" className="text-sm bg-white border border-teal-200 text-teal-700 px-4 py-2 rounded-lg hover:bg-teal-600 hover:text-white transition-colors font-medium">
                  Browse all 50 countries
                </Link>
                <Link href="/topics" className="text-sm bg-white border border-teal-200 text-teal-700 px-4 py-2 rounded-lg hover:bg-teal-600 hover:text-white transition-colors font-medium">
                  Browse all 25 topics
                </Link>
                <Link href="/about" className="text-sm bg-white border border-teal-200 text-teal-700 px-4 py-2 rounded-lg hover:bg-teal-600 hover:text-white transition-colors font-medium">
                  About KnowBefore
                </Link>
                <Link href="/privacy-policy" className="text-sm bg-white border border-teal-200 text-teal-700 px-4 py-2 rounded-lg hover:bg-teal-600 hover:text-white transition-colors font-medium">
                  Privacy Policy
                </Link>
              </div>
            </div>

          </div>
        </div>
      </main>
    </>
  )
}
