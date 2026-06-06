import type { Metadata, Viewport } from 'next'
import Script from 'next/script'
import './globals.css'

export const metadata: Metadata = {
  title: {
    default: 'KnowBefore — How Things Work in Every Country',
    template: '%s | KnowBefore',
  },
  description: 'Quick answers to everyday travel questions by country. Tipping, transport, healthcare, local laws and more — verified local knowledge.',
  metadataBase: new URL(process.env.NEXT_PUBLIC_SITE_URL || 'https://theknowbefore.com'),
  keywords: ['travel guide', 'country guide', 'tipping', 'local laws', 'tourist tips', 'travel tips'],
  authors: [{ name: 'KnowBefore' }],
  creator: 'KnowBefore',
  robots: { index: true, follow: true },
  icons: { icon: '/favicon.svg', shortcut: '/favicon.svg' },
  openGraph: {
    siteName: 'KnowBefore',
    type: 'website',
    locale: 'en_US',
  },
  twitter: {
    card: 'summary_large_image',
    site: '@knowbefore',
  },
  other: {
    'google-adsense-account': 'ca-pub-4634260205367551',
  },
}

export const viewport: Viewport = {
  width: 'device-width',
  initialScale: 1,
  themeColor: '#1e1d4c',
}

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <head>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossOrigin="anonymous" />
      </head>
      <body className="min-h-screen">
        {children}
        <Script
          async
          src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js?client=ca-pub-4634260205367551"
          crossOrigin="anonymous"
          strategy="afterInteractive"
        />
      </body>
    </html>
  )
}
