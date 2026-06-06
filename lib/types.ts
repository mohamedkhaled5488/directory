export interface TopicEntry {
  slug: string
  title: string
  quick_answer: string
  detail: string
  tourist_tips: string[]
  warning: string | null
  last_verified: string
  affiliate_relevant: string[]
}

export interface CountryData {
  country: string
  slug: string
  emoji: string
  region: string
  currency: string
  language: string
  topics: Record<string, TopicEntry>
}

export interface CountryIndex {
  name: string
  slug: string
  emoji: string
  region: string
  topicCount: number
  mostSearched: string[]
}

export interface TopicIndex {
  slug: string
  title: string
  icon: string
  description: string
  hot?: boolean
}

export interface RegionIndex {
  name: string
  slug: string
  countries: string[]
}

export interface SiteIndex {
  countries: CountryIndex[]
  topics: TopicIndex[]
  regions?: RegionIndex[]
}

export interface CountryStats {
  timezone: string
  currency: string
  language: string
  plugType: string
  bestTime: string
}

export interface AffiliateItem {
  name: string
  description: string
  cta: string
  url: string
  icon: string
}

export interface AffiliateMap {
  [key: string]: AffiliateItem
}

export interface SearchResult {
  country: CountryIndex
  topic: TopicIndex
  topicEntry: TopicEntry
  url: string
}

export interface LightSearchResult {
  countryName: string
  countryEmoji: string
  topicTitle: string
  quickAnswer: string
  url: string
}
