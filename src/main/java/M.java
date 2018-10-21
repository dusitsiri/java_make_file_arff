public class M {
    public static void main(String[] args) {
        String str = "Using Google Ads to Target Users Based on their Location in South Africa #Digital #Marketing #Advertising #SocialMedia #Entrepreneur #SEO #GrowthHacking #SMM #Blogging #SEOTalk #UX #Startup";
        if (str.toLowerCase().contains("startup")) {
            System.out.println(true);
            System.out.println(str.indexOf(str.toLowerCase()));
        }
    }
}
