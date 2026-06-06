import { CountryData, SiteIndex, AffiliateMap, TopicEntry, SearchResult, LightSearchResult, TopicIndex, RegionIndex } from './types'
import indexData from '@/data/index.json'
import affiliatesData from '@/data/affiliates.json'

// Static imports — bundler can resolve these at build time
import japanData from '@/data/countries/japan.json'
import uaeData from '@/data/countries/uae.json'
import thailandData from '@/data/countries/thailand.json'
import germanyData from '@/data/countries/germany.json'
import ukData from '@/data/countries/uk.json'
import franceData from '@/data/countries/france.json'
import australiaData from '@/data/countries/australia.json'
import singaporeData from '@/data/countries/singapore.json'
import italyData from '@/data/countries/italy.json'
import turkeyData from '@/data/countries/turkey.json'
import mexicoData from '@/data/countries/mexico.json'
import spainData from '@/data/countries/spain.json'
import brazilData from '@/data/countries/brazil.json'
import indiaData from '@/data/countries/india.json'
import chinaData from '@/data/countries/china.json'
import southKoreaData from '@/data/countries/south-korea.json'
import vietnamData from '@/data/countries/vietnam.json'
import greeceData from '@/data/countries/greece.json'
import portugalData from '@/data/countries/portugal.json'
import netherlandsData from '@/data/countries/netherlands.json'
import canadaData from '@/data/countries/canada.json'
import indonesiaData from '@/data/countries/indonesia.json'
import malaysiaData from '@/data/countries/malaysia.json'
import egyptData from '@/data/countries/egypt.json'
import moroccoData from '@/data/countries/morocco.json'
import saudiArabiaData from '@/data/countries/saudi-arabia.json'
import qatarData from '@/data/countries/qatar.json'
import southAfricaData from '@/data/countries/south-africa.json'
import newZealandData from '@/data/countries/new-zealand.json'
import philippinesData from '@/data/countries/philippines.json'
import argentinaData from '@/data/countries/argentina.json'
import colombiaData from '@/data/countries/colombia.json'
import costaRicaData from '@/data/countries/costa-rica.json'
import peruData from '@/data/countries/peru.json'
import switzerlandData from '@/data/countries/switzerland.json'
import austriaData from '@/data/countries/austria.json'
import czechRepublicData from '@/data/countries/czech-republic.json'
import polandData from '@/data/countries/poland.json'
import swedenData from '@/data/countries/sweden.json'
import norwayData from '@/data/countries/norway.json'
import croatiaData from '@/data/countries/croatia.json'
import hungaryData from '@/data/countries/hungary.json'
import jordanData from '@/data/countries/jordan.json'
import sriLankaData from '@/data/countries/sri-lanka.json'
import nepalData from '@/data/countries/nepal.json'
import hongKongData from '@/data/countries/hong-kong.json'
import maldivesData from '@/data/countries/maldives.json'
import kenyaData from '@/data/countries/kenya.json'
import tanzaniaData from '@/data/countries/tanzania.json'
import usData from '@/data/countries/us.json'

const COUNTRY_MAP: Record<string, CountryData> = {
  japan:          japanData as unknown as CountryData,
  uae:            uaeData as unknown as CountryData,
  thailand:       thailandData as unknown as CountryData,
  germany:        germanyData as unknown as CountryData,
  uk:             ukData as unknown as CountryData,
  france:         franceData as unknown as CountryData,
  australia:      australiaData as unknown as CountryData,
  singapore:      singaporeData as unknown as CountryData,
  italy:          italyData as unknown as CountryData,
  turkey:         turkeyData as unknown as CountryData,
  mexico:         mexicoData as unknown as CountryData,
  spain:          spainData as unknown as CountryData,
  brazil:         brazilData as unknown as CountryData,
  india:          indiaData as unknown as CountryData,
  china:          chinaData as unknown as CountryData,
  'south-korea':  southKoreaData as unknown as CountryData,
  vietnam:        vietnamData as unknown as CountryData,
  greece:         greeceData as unknown as CountryData,
  portugal:       portugalData as unknown as CountryData,
  netherlands:    netherlandsData as unknown as CountryData,
  canada:         canadaData as unknown as CountryData,
  indonesia:      indonesiaData as unknown as CountryData,
  malaysia:       malaysiaData as unknown as CountryData,
  egypt:          egyptData as unknown as CountryData,
  morocco:        moroccoData as unknown as CountryData,
  'saudi-arabia': saudiArabiaData as unknown as CountryData,
  qatar:          qatarData as unknown as CountryData,
  'south-africa': southAfricaData as unknown as CountryData,
  'new-zealand':  newZealandData as unknown as CountryData,
  philippines:    philippinesData as unknown as CountryData,
  argentina:      argentinaData as unknown as CountryData,
  colombia:       colombiaData as unknown as CountryData,
  'costa-rica':   costaRicaData as unknown as CountryData,
  peru:           peruData as unknown as CountryData,
  switzerland:    switzerlandData as unknown as CountryData,
  austria:        austriaData as unknown as CountryData,
  'czech-republic': czechRepublicData as unknown as CountryData,
  poland:         polandData as unknown as CountryData,
  sweden:         swedenData as unknown as CountryData,
  norway:         norwayData as unknown as CountryData,
  croatia:        croatiaData as unknown as CountryData,
  hungary:        hungaryData as unknown as CountryData,
  jordan:         jordanData as unknown as CountryData,
  'sri-lanka':    sriLankaData as unknown as CountryData,
  nepal:          nepalData as unknown as CountryData,
  'hong-kong':    hongKongData as unknown as CountryData,
  maldives:       maldivesData as unknown as CountryData,
  kenya:          kenyaData as unknown as CountryData,
  tanzania:       tanzaniaData as unknown as CountryData,
  us:             usData as unknown as CountryData,
}

const TIMEZONE_MAP: Record<string, string> = {
  japan: 'UTC+9', uae: 'UTC+4', thailand: 'UTC+7', germany: 'UTC+1/+2',
  uk: 'UTC+0/+1', france: 'UTC+1/+2', australia: 'UTC+8–+11', singapore: 'UTC+8',
  italy: 'UTC+1/+2', turkey: 'UTC+3', mexico: 'UTC−6/−8', spain: 'UTC+1/+2',
  brazil: 'UTC−3', india: 'UTC+5:30', china: 'UTC+8', 'south-korea': 'UTC+9',
  vietnam: 'UTC+7', greece: 'UTC+2/+3', portugal: 'UTC+0/+1', netherlands: 'UTC+1/+2',
  canada: 'UTC−3.5–−8', indonesia: 'UTC+7–+9', malaysia: 'UTC+8', egypt: 'UTC+2',
  morocco: 'UTC+1', 'saudi-arabia': 'UTC+3', qatar: 'UTC+3', 'south-africa': 'UTC+2',
  'new-zealand': 'UTC+12/+13', philippines: 'UTC+8', argentina: 'UTC−3', colombia: 'UTC−5',
  'costa-rica': 'UTC−6', peru: 'UTC−5', switzerland: 'UTC+1/+2', austria: 'UTC+1/+2',
  'czech-republic': 'UTC+1/+2', poland: 'UTC+1/+2', sweden: 'UTC+1/+2', norway: 'UTC+1/+2',
  croatia: 'UTC+1/+2', hungary: 'UTC+1/+2', jordan: 'UTC+3', 'sri-lanka': 'UTC+5:30',
  nepal: 'UTC+5:45', 'hong-kong': 'UTC+8', maldives: 'UTC+5', kenya: 'UTC+3',
  tanzania: 'UTC+3', us: 'UTC−5–−10',
}

export function getSiteIndex(): SiteIndex {
  return indexData as SiteIndex
}

export function getAllTopics(): TopicIndex[] {
  return getSiteIndex().topics
}

export function getTopicBySlug(slug: string): TopicIndex | null {
  return getAllTopics().find((t) => t.slug === slug) ?? null
}

export function getCountryTopic(countrySlug: string, topicSlug: string): TopicEntry | null {
  const country = getCountryData(countrySlug)
  return country?.topics[topicSlug] ?? null
}

export function getAllRegions(): RegionIndex[] {
  const { regions } = getSiteIndex() as SiteIndex & { regions: RegionIndex[] }
  return regions ?? []
}

export function getAffiliates(): AffiliateMap {
  return affiliatesData as AffiliateMap
}

export function getAllCountrySlugs(): string[] {
  return Object.keys(COUNTRY_MAP)
}

export function getCountryData(slug: string): CountryData | null {
  return COUNTRY_MAP[slug] ?? null
}

export function getAllCountriesData(): CountryData[] {
  return Object.values(COUNTRY_MAP)
}

export function getTopicAcrossCountries(topicSlug: string): Array<{
  country: CountryData
  topic: TopicEntry
}> {
  return getAllCountriesData()
    .filter((c) => topicSlug in c.topics)
    .map((c) => ({ country: c, topic: c.topics[topicSlug] }))
}

export function getRelatedCountries(
  currentSlug: string,
  currentRegion: string,
  count = 3
): CountryData[] {
  const all = getAllCountriesData()
  const sameRegion = all.filter((c) => c.slug !== currentSlug && c.region === currentRegion)
  const others = all.filter((c) => c.slug !== currentSlug && c.region !== currentRegion)
  return [...sameRegion, ...others].slice(0, count)
}

export function getCountriesByRegion(regionSlug: string): CountryData[] {
  const { regions } = getSiteIndex() as SiteIndex & { regions: Array<{ slug: string; countries: string[] }> }
  const region = regions.find((r) => r.slug === regionSlug)
  if (!region) return []
  return region.countries.map((s) => getCountryData(s)).filter((c): c is CountryData => c !== null)
}

export function getCountryStats(country: CountryData) {
  const electricity = country.topics['electricity-and-plugs']
  const weather = country.topics['weather-and-best-time']

  const plugMatch = electricity?.quick_answer?.match(/Type\s+[A-Z][/A-Z]*/)?.[0] ?? 'Universal'
  const bestTimeRaw = weather?.quick_answer ?? ''
  const bestTime = bestTimeRaw.length > 60 ? bestTimeRaw.slice(0, 57) + '…' : bestTimeRaw

  return {
    timezone: TIMEZONE_MAP[country.slug] ?? 'UTC varies',
    currency: country.currency,
    language: country.language,
    plugType: plugMatch,
    bestTime,
  }
}

export function getCompareData(slug1: string, slug2: string) {
  const c1 = getCountryData(slug1)
  const c2 = getCountryData(slug2)
  if (!c1 || !c2) return null
  const { topics: topicIndex } = getSiteIndex()
  return { country1: c1, country2: c2, topicIndex }
}

export function buildLightSearchIndex(): LightSearchResult[] {
  const { topics: topicIndex } = getSiteIndex()
  const topicTitleMap = Object.fromEntries(topicIndex.map((t) => [t.slug, t.title]))
  const results: LightSearchResult[] = []

  for (const country of getAllCountriesData()) {
    for (const [topicSlug, topicEntry] of Object.entries(country.topics)) {
      results.push({
        countryName: country.country,
        countryEmoji: country.emoji,
        topicTitle: topicTitleMap[topicSlug] ?? topicEntry.title,
        quickAnswer: topicEntry.quick_answer,
        url: `/${country.slug}/${topicSlug}`,
      })
    }
  }
  return results
}

export function buildSearchIndex(): SearchResult[] {
  const { topics: topicIndex } = getSiteIndex()
  const topicMap = Object.fromEntries(topicIndex.map((t) => [t.slug, t]))
  const results: SearchResult[] = []

  for (const country of getAllCountriesData()) {
    const countryIndex = {
      name: country.country,
      slug: country.slug,
      emoji: country.emoji,
      region: country.region,
      topicCount: Object.keys(country.topics).length,
      mostSearched: [],
    }
    for (const [topicSlug, topicEntry] of Object.entries(country.topics)) {
      const topicMeta = topicMap[topicSlug]
      if (!topicMeta) continue
      results.push({ country: countryIndex, topic: topicMeta, topicEntry, url: `/${country.slug}/${topicSlug}` })
    }
  }
  return results
}

export function getQuickAnswerSentiment(
  warning: string | null,
  quickAnswer: string
): 'green' | 'yellow' | 'red' {
  if (warning) return 'red'
  const lower = quickAnswer.toLowerCase()
  if (
    lower.includes('do not') || lower.includes("don't") || lower.includes('illegal') ||
    lower.includes('banned') || lower.includes('prohibited') || lower.includes('forbidden') ||
    lower.includes('strict') || lower.includes('arrest') || lower.includes('fine')
  ) return 'yellow'
  return 'green'
}
