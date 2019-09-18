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
