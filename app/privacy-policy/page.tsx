import type { Metadata } from 'next'
import Link from 'next/link'
import Navigation from '@/components/Navigation'

export const metadata: Metadata = {
  title: 'Privacy Policy — KnowBefore',
  description: 'Privacy policy for KnowBefore. Learn how we collect, use, and protect your information when you use our travel information directory.',
  alternates: { canonical: '/privacy-policy' },
}

export default function PrivacyPolicyPage() {
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
              <span className="text-gray-900 font-medium">Privacy Policy</span>
            </nav>
          </div>
        </div>

        <div className="bg-white border-b border-gray-100">
          <div className="container-main py-12">
            <h1 className="text-4xl font-extrabold text-gray-900 mb-3">Privacy Policy</h1>
            <p className="text-gray-500 text-sm">Last updated: June 2025</p>
          </div>
        </div>

        <div className="container-main py-12">
          <div className="max-w-3xl space-y-10 prose prose-gray">

            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">1. Introduction</h2>
              <p className="text-gray-600 leading-relaxed mb-4">
                Welcome to KnowBefore (&quot;we&quot;, &quot;our&quot;, or &quot;us&quot;), accessible at{' '}
                <strong>theknowbefore.com</strong>. We are committed to protecting your personal information and
                your right to privacy.
              </p>
              <p className="text-gray-600 leading-relaxed">
                This Privacy Policy explains what information we collect, how we use it, and what rights you
                have in relation to it. Please read it carefully.
              </p>
            </section>

            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">2. Information We Collect</h2>
              <p className="text-gray-600 leading-relaxed mb-4">
                KnowBefore is an informational website. We do not require you to create an account or submit
                personal information to use our service. However, the following data may be collected automatically:
              </p>
              <div className="space-y-3">
                {[
                  {
                    title: 'Usage Data',
                    desc: 'Pages visited, time spent on pages, referring URLs, browser type, device type, and general geographic location (country/city level). This is collected via Google Analytics.',
                  },
                  {
                    title: 'Cookies',
                    desc: 'We use cookies for analytics and advertising purposes. You can control cookies through your browser settings.',
                  },
                  {
                    title: 'Log Data',
                    desc: 'Our hosting provider may collect standard server logs including IP addresses, browser types, and access times.',
                  },
                ].map((item) => (
                  <div key={item.title} className="bg-white rounded-xl border border-gray-100 p-5">
                    <p className="font-semibold text-gray-900 mb-1">{item.title}</p>
                    <p className="text-gray-600 text-sm leading-relaxed">{item.desc}</p>
                  </div>
                ))}
              </div>
            </section>

            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">3. How We Use Your Information</h2>
              <p className="text-gray-600 leading-relaxed mb-4">We use the information we collect to:</p>
              <ul className="space-y-2 text-gray-600">
                {[
                  'Understand how visitors use our site so we can improve it',
                  'Monitor site performance and fix technical issues',
                  'Analyse which content is most useful to our readers',
                  'Display relevant advertisements through Google AdSense',
                  'Comply with legal obligations',
                ].map((item) => (
                  <li key={item} className="flex items-start gap-2">
                    <span className="mt-1.5 flex-shrink-0 w-1.5 h-1.5 rounded-full bg-teal-500" />
                    {item}
                  </li>
                ))}
              </ul>
            </section>

            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">4. Third-Party Services</h2>
              <p className="text-gray-600 leading-relaxed mb-4">
                We use the following third-party services which may collect data independently:
              </p>
              <div className="space-y-3">
                {[
                  {
                    name: 'Google Analytics',
                    desc: 'We use Google Analytics to understand site traffic and usage patterns. Google may use this data in accordance with their own privacy policy.',
                    link: 'https://policies.google.com/privacy',
                  },
                  {
                    name: 'Google AdSense',
                    desc: 'We display advertisements through Google AdSense. Google uses cookies to serve ads based on prior visits to our site or other sites. You can opt out of personalised advertising by visiting Google\'s Ads Settings.',
                    link: 'https://policies.google.com/technologies/ads',
                  },
                ].map((service) => (
                  <div key={service.name} className="bg-white rounded-xl border border-gray-100 p-5">
                    <p className="font-semibold text-gray-900 mb-1">{service.name}</p>
                    <p className="text-gray-600 text-sm leading-relaxed">{service.desc}</p>
                  </div>
                ))}
              </div>
            </section>

            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">5. Cookies</h2>
              <p className="text-gray-600 leading-relaxed mb-4">
                Cookies are small text files stored on your device when you visit a website. We use the following types:
              </p>
              <ul className="space-y-2 text-gray-600">
                <li className="flex items-start gap-2">
                  <span className="mt-1.5 flex-shrink-0 w-1.5 h-1.5 rounded-full bg-teal-500" />
                  <span><strong>Analytics cookies</strong> — set by Google Analytics to track visit statistics</span>
                </li>
                <li className="flex items-start gap-2">
                  <span className="mt-1.5 flex-shrink-0 w-1.5 h-1.5 rounded-full bg-teal-500" />
                  <span><strong>Advertising cookies</strong> — set by Google AdSense to serve relevant ads</span>
                </li>
              </ul>
              <p className="text-gray-600 leading-relaxed mt-4">
                You can disable cookies in your browser settings at any time. Disabling cookies may affect
                how parts of the site display.
              </p>
            </section>

            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">6. Data Retention</h2>
              <p className="text-gray-600 leading-relaxed">
                We do not store personal data on our own servers. Analytics data is retained by Google
                Analytics for 26 months by default. You may request deletion of your data through Google&apos;s
                own controls.
              </p>
            </section>

            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">7. Your Rights</h2>
              <p className="text-gray-600 leading-relaxed mb-4">
                Depending on your location, you may have the following rights regarding your data:
              </p>
              <ul className="space-y-2 text-gray-600">
                {[
                  'The right to access information we hold about you',
                  'The right to request correction of inaccurate data',
                  'The right to request deletion of your data',
                  'The right to object to processing of your data',
                  'The right to opt out of targeted advertising',
                ].map((right) => (
                  <li key={right} className="flex items-start gap-2">
                    <span className="mt-1.5 flex-shrink-0 w-1.5 h-1.5 rounded-full bg-teal-500" />
                    {right}
                  </li>
                ))}
              </ul>
              <p className="text-gray-600 leading-relaxed mt-4">
                To exercise any of these rights, please contact us at the address below.
              </p>
            </section>

            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">8. Children&apos;s Privacy</h2>
              <p className="text-gray-600 leading-relaxed">
                KnowBefore is not directed at children under 13 years of age. We do not knowingly collect
                personal information from children. If you believe a child has provided us with personal
                information, please contact us so we can delete it.
              </p>
            </section>

            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">9. Changes to This Policy</h2>
              <p className="text-gray-600 leading-relaxed">
                We may update this Privacy Policy from time to time. When we do, we will revise the
                &quot;Last updated&quot; date at the top of this page. We encourage you to review this policy
                periodically.
              </p>
            </section>

            <section>
              <h2 className="text-2xl font-bold text-gray-900 mb-4">10. Contact Us</h2>
              <p className="text-gray-600 leading-relaxed mb-4">
                If you have any questions or concerns about this Privacy Policy or our data practices,
                please contact us:
              </p>
              <div className="bg-white rounded-xl border border-gray-100 p-5">
                <p className="font-semibold text-gray-900 mb-1">KnowBefore</p>
                <p className="text-gray-600 text-sm">Website: <strong>theknowbefore.com</strong></p>
                <p className="text-gray-600 text-sm mt-1">
                  Contact form: <Link href="/contact" className="text-teal-600 hover:underline">/contact</Link>
                </p>
              </div>
            </section>

          </div>
        </div>
      </main>
    </>
  )
}
