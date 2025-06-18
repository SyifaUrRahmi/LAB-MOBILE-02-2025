package com.example.booklibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookRepository {
    private static BookRepository instance;
    private List<Book> books;
    private ExecutorService executorService;

    private BookRepository() {
        books = new ArrayList<>();
        executorService = Executors.newSingleThreadExecutor();
        loadDummyBooksInBackground();
    }

    public static synchronized BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    private void loadDummyBooksInBackground() {
        executorService.execute(this::addDummyBooks);
    }

    private void addDummyBooks() {
        books.add(new Book("The Lost Pirates", "John Blackshore", "Mengisahkan petualangan sekelompok bajak laut yang terdampar di pulau misterius setelah kapal mereka karam. Dengan peta tua dan rahasia kuno yang mulai terungkap, mereka harus bertahan dari makhluk legenda, jebakan kuno, dan pengkhianatan di antara kru demi menemukan harta karun yang telah lama hilang.\n", 2025, "Petualangan", "https://i.pinimg.com/736x/05/9d/94/059d943f5352addba4bf31305a08aacd.jpg", 4.7));
        books.add(new Book("NightBooks", "J.A. White", "Alex suka menulis cerita horor, tapi ia merasa tidak ada yang memahami dirinya. Suatu malam, ia tertarik oleh suara misterius dan terjebak di dalam apartemen penyihir jahat. Untuk bertahan hidup, Alex harus menceritakan kisah menyeramkan setiap malam—dan menemukan cara melarikan diri sebelum ia menjadi bagian dari koleksi sang penyihir selamanya.\n", 2023, "Horor", "https://i.pinimg.com/736x/a4/db/9b/a4db9be58196c7d4a0364a39bcaaa259.jpg", 4.6));
        books.add(new Book("The Girl of Precinct Five",
                "Ariana Blackwell",
                "Di tengah kota yang dilanda kejahatan, seorang detektif muda bernama Clara menemukan seorang gadis misterius yang mengaku kehilangan ingatan. Gadis itu terhubung dengan kasus-kasus pembunuhan yang belum terpecahkan di Precinct Five. Bersama-sama, mereka mengungkap jaringan rahasia dan korupsi yang lebih dalam dari yang pernah dibayangkan Clara.”\n",
                2025,
                "Misteri",
                "https://i.pinimg.com/736x/06/5a/bd/065abd77671765f86bd171dc22854985.jpg",
                4.5));
        books.add(new Book("The 13th God",
                "Elias Thorn",
                "Dalam dunia kuno yang dikuasai oleh dua belas dewa, muncul ramalan tentang kemunculan dewa ketiga belas yang akan membawa kehancuran atau keselamatan umat manusia. Seorang pemuda yatim piatu yang tak menyadari takdirnya, harus menghadapi konflik para dewa, membangkitkan kekuatan dalam dirinya, dan memilih sisi dalam perang antara langit dan neraka.\n",
                2025,
                "Fantasi",
                "https://i.pinimg.com/736x/f9/98/48/f99848efef142d8dbabf0adb1adc61ad.jpg",
                4.9));
        books.add(new Book("The Conjurer: Fight of the Fallen",
                "Lysandra Vale",
                "Setelah kekaisaran sihir runtuh, para penyihir yang tersisa hidup dalam bayang-bayang. Kael, seorang pemuda dengan kekuatan terlarang sebagai Conjurer, memimpin perlawanan melawan tirani baru yang dipimpin oleh Dewan Bayangan. Di tengah pertempuran antara kebaikan dan kejatuhan, Kael harus memilih: menyelamatkan dunia atau mengorbankan segalanya demi kebenaran yang kelam.\n",
                2025,
                "Fantasi Aksi",
                "https://i.pinimg.com/736x/cb/fe/be/cbfebea957e3e7962635f730d04f6ac5.jpg",
                4.8));
        books.add(new Book("Martin Si Anak Magang",
                "Vernando Altamirano",
                "Halooo para Lapet!! Selamat datang di komik \"Martín sí Anak Magang\"! Komik dí mana sí Martin selalu buat salah dan gak pernah betul lah tingkahnya di kantor kutengok. Sampai-sampai bosnya si Bang Mikhail naik darah dibuatnya. Kalian penasaran? Mau naik darah sambil ketawa kan Lapet? Ayolah baca komik ini Lapet! Komik ini juga berisi cerita-cerita eksklusif yang belum pernah dipublikasikan di mana pun oleh Vernalta",
                2024,
                "Humor",
                "https://image.gramedia.net/rs:fit:0:0/plain/https://cdn.gramedia.com/uploads/picture_meta/2024/3/21/kh7zwq4mlbazp9bsnwzamg.jpg",
                4.3));
        books.add(new Book("Seporsi Mie Ayam Sebelum Mati",
                "Brian Khrisna",
                "Ale, seorang pria berusia 37 tahun memiliki tinggi badan 189 cm dan berat 138 kg. Badannya bongsor, berkulit hitam, dan memiliki masalah dengan bau badan. Sejak kecil, Ale hidup di lingkungan keluarga yang tidak mendukungnya. Ia tak memiliki teman dekat dan menjadi korban perundungan di sekolahnya",
                2024,
                "Kehidupan Kota",
                "https://image.gramedia.net/rs:fit:0:0/plain/https://cdn.gramedia.com/uploads/products/95ob5m98ur.jpg",
                4.1));
        books.add(new Book("Ayah Berjuang Sendiri Itu Capek!",
                "Adellia P. D.",
                "Menjalani kehidupan sebagai mahasiswi akhir kedokteran dengan beasiswa saja, sudah membuat Brichia sering memijat kepala. Cobaan demi cobaan silih berganti menghampiri hidupnya.\n" +
                        "\n" +
                        "Dunia Brichia yang sudah rapuh, kini benar-benar runtuh setelah ayahnya jatuh sakit. la rela berjualan ayam potong menggantikan ayahnya yang sakit parah. Belum lagi hubungan dengan kakaknya, Nafaesa, yang kurang baik.\n",
                2024,
                "Kehidupan Kota",
                "https://image.gramedia.net/rs:fit:0:0/plain/https://cdn.gramedia.com/uploads/product-metas/snab-g1-y2.jpg",
                4.1));
        books.add(new Book("The Seekers of Emet: The Eighth Renewal Saga",
                "Noah Elrond",
                "Setelah menemukan sebagian kebenaran tentang Emet, para Seeker kini menghadapi takdir yang lebih besar—menghentikan Siklus Kegelapan yang telah berulang selama tujuh era. Dalam saga kedelapan ini, mereka ditantang oleh dewa-dewa lama, pengkhianatan dari dalam, dan waktu yang semakin menipis. Hanya melalui pengorbanan dan keberanian sejati mereka bisa membuka jalan menuju pembaruan dunia.",
                2025,
                "Fantasi Epik",
                "https://i.pinimg.com/736x/1b/bb/0a/1bbb0adfcb72afaa93a909ae97d052a6.jpg",
                4.9));

        books.add(new Book("Beyond the Black Door", "A.M. Strickland", "‘Kamai selalu tahu bahwa dia berbeda—sebagai seorang soulwalker, ia dapat menjelajahi jiwa orang lain. Tapi ada satu aturan yang tak pernah ia langgar: jangan pernah membuka pintu hitam. Ketika tragedi memaksanya melanggar aturan itu, Kamai terjebak dalam dunia gelap penuh rahasia, godaan, dan kebenaran tentang dirinya yang jauh lebih menakutkan dari yang pernah ia bayangkan.",2024 , "Dark Fantasy", "https://i.pinimg.com/736x/12/9b/6f/129b6fec6678ef6f56a835808e97eff2.jpg",  4.5));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void updateFavoriteStatus(String title, boolean isFavorite) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                book.setFavorite(isFavorite);
                break;
            }
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }
}