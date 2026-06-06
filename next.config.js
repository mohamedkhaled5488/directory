/** @type {import('next').NextConfig} */
const nextConfig = {
  // Remove 'standalone' for Vercel/standard deployment; add back for Docker
  experimental: {
    optimizePackageImports: ['tailwindcss'],
  },
}

module.exports = nextConfig
