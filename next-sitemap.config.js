/** @type {import('next-sitemap').IConfig} */
module.exports = {
  siteUrl: process.env.SITE_URL || 'https://worldhow.com',
  generateRobotsTxt: true,
  generateIndexSitemap: false,
  changefreq: 'monthly',
  robotsTxtOptions: {
    policies: [{ userAgent: '*', allow: '/' }],
  },
  transform: async (config, path) => {
    let priority = 0.7
    let changefreq = 'monthly'

    if (path === '/') {
      priority = 1.0
      changefreq = 'weekly'
    } else if (path === '/countries' || path === '/topics') {
      priority = 0.7
    } else if (path.startsWith('/regions/')) {
      priority = 0.6
    } else if (path.startsWith('/compare/')) {
      priority = 0.8
    } else if (path.startsWith('/topics/')) {
      priority = 0.7
    } else if (/^\/[^/]+\/[^/]+$/.test(path)) {
      // /[country]/[topic] — main SEO pages, highest priority
      priority = 0.9
    } else if (/^\/[^/]+$/.test(path)) {
      // /[country] — country pages
      priority = 0.8
    }

    return {
      loc: path,
      changefreq,
      priority,
      lastmod: new Date().toISOString(),
    }
  },
}
