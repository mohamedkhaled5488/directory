import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'

export const metadata: Metadata = {
  title: 'About KnowBefore — Our Mission & Editorial Standards',
  description: 'KnowBefore is a travel information directory helping tourists understand local customs, laws, and practical tips across 50 countries. Learn about our editorial process.',
  alternates: { canonical: '/about' },
}

export default function AboutPage() {
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
              <span className="text-gray-900 font-medium">About</span>
            </nav>
          </div>
        </div>

        {/* Hero */}
        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-12">
            <h1 className="text-4xl font-extrabold text-gray-900 mb-4">About KnowBefore</h1>
            <p className="text-xl text-gray-600 max-w-2xl">
              Practical travel knowledge — verified, structured, and always free to access.
            </p>
          </div>
        </div>

        <div className="container-main py-12">
          <div className="max-w-3xl space-y-10">

            {/* Mission */}
            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">Our Mission</h2>
              <p className="text-gray-600 leading-relaxed mb-4">
                KnowBefore exists to answer one question every traveller asks before landing somewhere new:
                <em> "How does this actually work here?"</em>
              </p>
              <p className="text-gray-600 leading-relaxed mb-4">
                Whether it&apos;s understanding tipping culture in Japan, knowing which medications are illegal in the UAE,
                or figuring out how to use public transport in Germany — we provide clear, practical answers that
                help you travel with confidence instead of guessing.
              </p>
              <p className="text-gray-600 leading-relaxed">
                We cover <strong>50 countries</strong> and <strong>25 essential travel topics</strong> — 1,250 individual guides
                written to give you the real picture, not a watered-down tourist brochure version.
              </p>
            </section>

            {/* What We Cover */}
            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">What We Cover</h2>
              <p className="text-gray-600 leading-relaxed mb-4">
                Every country guide on KnowBefore covers 25 topics that matter most to travellers:
              </p>
              <div className="grid grid-cols-2 sm:grid-cols-3 gap-3">
                {[
                  '💵 Tipping culture',
                  '🚌 Public transport',
                  '🏥 Tourist healthcare',
                  '⚖️ Local laws',
                  '🚨 Emergency numbers',
                  '👗 Dress code',
                  '🍺 Alcohol rules',
                  '💧 Water safety',
                  '🚕 Taxis & rideshare',
                  '📱 SIM & internet',
                  '🔌 Electricity & plugs',
                  '🔒 Crime & safety',
                  '🚗 Driving rules',
                  '🌤 Best time to visit',
                  '💰 Money-saving tips',
                ].map((item) => (
                  <div key={item} className="flex items-center gap-2 bg-white rounded-xl border border-gray-100 px-3 py-2 text-sm text-gray-700">
                    {item}
                  </div>
                ))}
              </div>
            </section>

            {/* Editorial Process */}
            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">Our Editorial Process</h2>
              <p className="text-gray-600 leading-relaxed mb-4">
                Every guide on KnowBefore is researched against multiple authoritative sources including official
                government tourism websites, embassy advisories, local government regulations, and established
                travel publications.
              </p>
              <div className="space-y-4">
                {[
                  {
                    title: 'Researched from official sources',
                    desc: 'We cross-reference government tourism boards, embassy guidance, and local authority publications for every country and topic.',
                  },
                  {
                    title: 'Practically focused',
                    desc: 'We filter for what actually matters to a visitor — not exhaustive legal text, but the practical rules a tourist needs to know.',
                  },
                  {
                    title: 'Regularly verified',
                    desc: 'Each guide includes a "last verified" date. We review and update guides when local laws, infrastructure, or customs change.',
                  },
                  {
                    title: 'Warning flags for high-risk topics',
                    desc: 'Where rules carry serious consequences (such as alcohol restrictions, drug laws, or dress code enforcement), we clearly flag warnings.',
                  },
                ].map((step) => (
                  <div key={step.title} className="flex gap-4 bg-white rounded-xl border border-gray-100 p-5">
                    <div className="flex-shrink-0 w-2 h-2 mt-2 rounded-full bg-teal-500" />
                    <div>
                      <p className="font-semibold text-gray-900 mb-1">{step.title}</p>
                      <p className="text-gray-600 text-sm leading-relaxed">{step.desc}</p>
                    </div>
                  </div>
                ))}
              </div>
            </section>

            {/* Disclaimer */}
            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">Important Disclaimer</h2>
              <div className="bg-amber-50 border border-amber-200 rounded-xl p-5">
                <p className="text-amber-800 text-sm leading-relaxed mb-3">
                  KnowBefore provides general travel information for educational purposes. Our guides are not legal
                  advice, medical advice, or a substitute for official government guidance.
                </p>
                <p className="text-amber-800 text-sm leading-relaxed">
                  Laws and local customs change. Always verify critical information — especially regarding local laws,
                  medication rules, and health requirements — with official sources and your country&apos;s embassy
                  before travelling.
                </p>
              </div>
            </section>

            {/* Contact CTA */}
            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">Get in Touch</h2>
              <p className="text-gray-600 leading-relaxed mb-4">
                Found outdated information? Have a suggestion for a new country or topic? We want to hear from you.
              </p>
              <Link
                href="/contact"
                className="inline-flex items-center gap-2 bg-teal-600 text-white px-6 py-3 rounded-xl font-medium hover:bg-teal-700 transition-colors"
              >
                Contact Us →
              </Link>
            </section>

          </div>
        </div>
      </main>
    </>
  )
}
