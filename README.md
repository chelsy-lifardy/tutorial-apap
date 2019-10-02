# Tutorial APAP

## Authors

- **Chelsy Lifardy** - _1706043582_ - _A_

---

## Tutorial 1

### What I have learned today

pada lab tutorial ini, saya belajar mengenai penggunaan git, membuat architectural pattern yaitu Model-View-Controller (MVC). Saya menjadi tahu bahwa untuk membuat Model dan Controller dilakukan dengan membuat package, sedangkan view diatur di file html dengan menggunakan Thymeleaf. Controller mengurus hal terkait logika bisnis dari website tersebut.

#### Github

1. Apa itu Issue Tracker? Masalah apa yang dapat diselesaikan dengan Issue Tracker?

   - Issue Tracker adalah sebuat fitur yang memungkinkan kita untuk menyimpan dan mengikuti perkembangan setiap issue yang diidetifikasi oleh developer sampai issue tersebut teratasi.
   - Masalah yang dapat diselesaikan Issue Tracker dapat berupa: Bug, Internal Cleanup, Duplicate Pull Request, Feature Request, Invalid Code

2. Apa perbedaan dari git merge dan merge --squash?

   - Pada git merge, semua histori commit dari setiap branch akan ditambahkan di dalam branch utama atau master ketika dilakukan merge commit. Sedangkan, pada merge --squash, commit dari setiap pull request akan digabungkan menjadi single commit.

#### Spring

3. Apa itu library & dependency?

   - Library adalah sekelompok kelas yang memiliki function yang sama dan dikumpulkan mennjadi satu supaya dapat digunakan dengan mudah pada proyek lainnya.
   - Dependency adalah library Java eksternal yang diperlukan untuk suatu proyek. Contoh dependency yang kita gunakan: Spring Web, Thymeleaf, Spring boot DevTools.

4. Apa itu Maven? Mengapa kita perlu menggunakan Maven?

   - Maven adalah tool yang digunakan untuk melakukan automation build pada proyek Java. Dalam maven, terdapat 2 aspek dalam membangun software, yaitu dapat mendeskripsikan bagaimana software dibangun serta mendeskripsikan dependensinya. Maven akan secara dinamis mengunduh library Java dan plug-in Maven. Selain itu, maven dapat digunakan untuk mengelola proyek yang ditulis dengan bahasa pemrograman lainnya seperti C#, Ruby, Scala, dan lainnya.
   - Supaya struktur direktori setiap orang yang berbeda dapat mengikuti standar project template yang sudah ada. Dengan menggunakan maven, kita hanya perlu membuat 1 buah archetype untuk 1 jenis proyek. Selain itu, maven dapat digunakan untuk mengelola dependency sehingga programmer dapat dengan mudah menambahkan library yang ingin digunakan. Terakhir, maven membantu kita dalam portability project yang memungkinkan kita untuk membuka project dengan IDE apapun.

5. Apa alternatif dari Maven?

   - Jenkins, Gradle, Apache Ant, Teamwork, sbt

### What I did not understand

- [ ] Kapan menggunakan pathVariable dan requestParam
- [ ] Belum memahami flow dari Springboot
- [ ] Belum menghafal framework yang tersedia di dalam Spring

## Tutorial 2

### Pertanyaan 1: Cobalah untuk menambahkan sebuah store dengan mengakses link berikut: http://localhost:8080/store/add?idStore=1&nama=shapipi&keterangan=Toko%20Elektronik&followers=100

#### Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi

Pada tahap ini, saya belum memiliki view sehingga ketika saya memanggil URL tersebut, URL tersebut tidak ada. Sehingga, muncul error pada page tersebut.

<br>

### Pertanyaan 2: View template dengan nama “add-store.html” telah berhasil kamu buat. Jalankan programmu dan akses kembali link berikut: http://localhost:8080/store/add?idStore=1&nama=shapipi&keterangan=Toko%20Elektronik

#### Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi

Terjadi error dengan status 400 (type=Bad Request, status=400). Mengapa terjadi? karena terdapat kekurangan parameter yaitu parameter followers. Hal tersebut error karena ketika membuat model, parameter tersebut disetting required

<br>

### Pertanyaan 3: : Jika Papa APAP ingin melihat store shapee, link apa yang harus diakses?

dapat mengakses link berikut http://localhost:8080/restoran/view-all

<br>

### Pertanyaan 4: : Tambahkan 1 contoh store lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/store/view-all,

#### apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.

Daftar list seluruh store beserta dengan ID Store, Nama Store, Keterangan, dan Followers

<a href="https://ibb.co/j3TnZ8S"><img src="https://i.ibb.co/VMtfpLs/Screen-Shot-2019-09-18-at-17-32-38.png" alt="Screen-Shot-2019-09-18-at-17-32-38" border="0"></a>

## Tutorial 3

1. Pada class ProductDb, terdapat method findByStoreModelId, apakah kegunaan dari method tersebut?
   - findByStoreModelId pada ProductDb merupakan sebuah interface dari JPARepository untuk memberi implementasi untuk ProductService agar dapat melakukan query/pengambilan Store yang sesuai dengan Id
2. Pada class StoreController, jelaskan perbedaan method addStoreFormPage dan
   addStoreSubmit?
   - `addStoreFormPage` merupakan fungsi untuk memberikan halaman Form untuk menginput Store dari form, ini ditandai oleh request GET. `addStoreSubmit` merupakan fungsi untuk menyimpan store pada database, ini ditandai oleh request POST.
3. Jelaskan kegunaan dari JPA Repository
   - JPA Repository memberikan sebuah interface untuk melakukan mapping antara objek Java dengan model pada Database. Hal ini dikenal dengan ORM (Object Relational Mapping)
4. Sebutkan dan jelaskan di bagian kode mana sebuah relasi antara StoreModel dan
   ProductModel dibuat? - Relasi antara StoreModel dan ProductModel terletak pada cuplikan kode berikut.

   ```
   @OneToMany(mappedBy = "storeModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL) private List<ProductModel> listProduct;
   ```

   - Pada cuplikan kode pertama, model Store memiliki relasi oneToMany dengan model Product, yang menyatakan sebuah Store memiliki banyak Product. Relase oneToMany yang diberikan bersifat fetch lazy dan cascade all. Fetch lazy menyatakan bahwa inisialisasi model product tidak langsung dilakukan / disimpan di memory dan akan dilakukan ketika melalui getter. Cascade all berarti semua perubahan yang dilakukan pada store juga akan diubah di product

   ```
   @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name="storeid", referencedColumnName = "id", nullable = false) @OnDelete(action = OnDeleteAction.CASCADE) @JsonIgnore private StoreModel storeModel;
   ```

   - Sedangkan pada cuplikan kode kedua, model Product memiliki relasi manyToOne dengan model Store, yang meyatakan sebuah Product dimiliki oleh banyak Store. `@JoinColumn` akan memberikan kolom storeid pada model product sebagai foreign key. `@JsonIgnore` akan memberikan penanda bahwa atribut ini tidak diikutsertakan ketika dilakukan parsign JSON.

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER
   - FetchType.LAZY: Pengambilan model relasi dari sebuah model dilakukan ketika melakukan pemanggilan fungsi getter dari atribut relasi tersebut.
   - FetchType.EAGER: Pengambilan model relasi dari sebuah model dilakukan bersamaan ketika mengambil model tersebut dari database.
   - CascadeType.ALL: Semua operasi yang dilakukan oleh model akan mempengaruhi model relasinya termasuk PERSIST, REMOVE, REFRESH, MERGE, dan DETACH

## Tutorial 4

1. Jelaskan yang anda pelajari dari melakukan latihan nomor 2, dan jelaskan tahapan bagaimana
   anda menyelesaikan latihan nomor 2

   - Pada latihan nomor 2 saya mempelajari cara penggunaan dynamic fragment dimana kita dapat menempatkan fragment berbeda beda sesuai dengan HTML. Cara untuk menyelesaikan tahapan nomor 2 adalah dengan menggunakan parameter.

   - Saya menambahkan `th:fragment="navbar(page)"` di fragment.html dan implementasi di file html lain dengan `<nav th:replace="fragments/fragment :: navbar(page = ${page})"></nav>` yang nantinya parameter "page" akan direturn atributnya dari controller.

2. Jelaskan yang anda pelajari dari latihan nomor 3, dan jelaskan tahapan bagaimana anda
   menyelesaikan latihan nomor 3

   - Hal yg dipelajari adalah bagaimana controller merupakan perantara antara objek model dengan view tampilan html dan juga mengatur kapan objek model disimpan ke database.
   - Tahapann menyelesaikan latihan nomor 3 saya menggunakan konsep seperti ini:
     - tambah / hapus row dilakukan dengan memanggil postmapping pada controller
     - controller akan menambahkan / menghapus product kosong sebagai dummy pada objek model store, sehingga akan menambah / menghapus kolom baris pada form
     - ketika submit, karena row yang disubmit tidak kosong, maka product yang sudah di isi akan disimpan ke database

3. Jelaskan perbedaan th:include dan th:replace
   - th:include = mengambil konten dari fragment dan memasukkannya ke dalam host tagnya sendiri
   - th:replace = mengganti host tag nya dengan fragment

```
#contoh penggunaan th:include dan th:replace

    <body>
    ...
    <div th:include="footer :: copy"></div>
    <div th:replace="footer :: copy"></div>
    </body>

```

```
#hasil penggunaan th:include dan th:replace

    <body>

    ...

    <div>
        &copy; 2011 The Good Thymes Virtual Grocery
    </div>
    <footer>
        &copy; 2011 The Good Thymes Virtual Grocery
    </footer>

    </body>
```

4. Jelaskan bagaimana penggunaan th:object beserta tujuannya
   - th:object digunakan untuk menunjuk sebuah objek yang akan dimasukkan ke dalam form data yang dikirimkan.
   - value untuk menggunakan atribut th:object di dalam form tags harus menggunakan variable expressions (${...}) yang diisi dengan model atribut. Yang artinya (${manusia}) dapat digunakan, sedangkan (\${manusia.nama}) tidak dapat digunakan.
