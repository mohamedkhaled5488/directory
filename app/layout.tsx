import type { Metadata, Viewport } from 'next'
import './globals.css'

export const metadata: Metadata = {
  title: {
    default: 'WorldHow — How Things Work in Every Country',
    template: '%s | WorldHow',
  },
  description: 'Quick answers to everyday travel questions by country. Tipping, transport, healthcare, local laws and more — verified local knowledge.',
  metadataBase: new URL(process.env.NEXT_PUBLIC_SITE_URL || 'https://worldhow.com'),
  keywords: ['travel guide', 'country guide', 'tipping', 'local laws', 'tourist tips', 'travel tips'],
  authors: [{ name: 'WorldHow' }],
  creator: 'WorldHow',
  robots: { index: true, follow: true },
  icons: { icon: '/favicon.svg', shortcut: '/favicon.svg' },
  openGraph: {
    siteName: 'WorldHow',
    type: 'website',
    locale: 'en_US',
  },
  twitter: {
    card: 'summary_large_image',
    site: '@worldhow',
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
      <body className="min-h-screen">{children}</body>
    </html>
  )
}
