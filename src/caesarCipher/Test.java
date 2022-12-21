package caesarCipher;

public class Test {
    public static void main(String[] args) {
        String s1 = "My name is Balazs Holczer. I am from Budapest, Hungary. I am qualified as a physicist. At the moment I am working as a simulation engineer at a multinational company. I have been interested in algorithms and data structures and its implementations especially in Java since university. Later on I got acquainted with machine learning techniques, artificial intelligence, numerical methods and recipes such as solving differential equations, linear algebra, interpolation and extrapolation. These things may prove to be very very important in several fields: software engineering, research and development or investment banking. I have a special addiction to quantitative models such as the Black-Scholes model, or the Merton-model.";
        String s2 = "Hello world а теперь немного русского паххаха всмысле ттам же больше русского";

        DetectEnglishLang detectEnglishLang = new DetectEnglishLang();

        System.out.println(detectEnglishLang.isEnglish(s1));
        System.out.println(detectEnglishLang.isEnglish(s2));
    }
}
