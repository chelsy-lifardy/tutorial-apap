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

### Tutorial 5

1. Jelaskan bagian mana saja dari test yang dibuat pada latihan no 2 adalah given, when, dan and then.

   ```
   @Test
    public void whenFindStoreByIdAccessedItShouldReturnSelectedStorePage() throws Exception {
        // Given
        StoreModel store = generateDummyProductModel(1);

        // When
        when(storeService.getStoreById(1L)).thenReturn(Optional.of(store));
        mockMvc.perform(get("/store/view?idStore=1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                // Then
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("view-store"))
                .andExpect(model().attribute("store", is(store)));
        verify(storeService, times(2)).getStoreById(1L);
    }
   ```

   - `Given`: memberikan inisiasi objek model store
   - `When`: melakukan pemanggilan method view store
   - `Then`: mencocokan expected output dengan output yang sebenarnya

2. Jelaskan perbedaan `line coverage` dan `logic coverage`.
   - Line coverage: Mengukur berapa persen baris kode yang dijalankan dalam suatu test.
   - Logic coverage: Mengukur berapa persen kode yang di-test yang sesuai dengan logic program tersebut.
3. Pada keadaan ideal, apa yang seharusnya dibuat terlebih dahulu, kode atau unit test? Mengapa seperti itu? Apa akibatnya jika urutannya dibalik, adakah risiko tak terlihat yang mungkin terjadi?
   - Dalam kondisi ideal, unit test seharusnya dibuat terlebih dahulu. Dengan membuat unit test terlebih dahulu, maka risiko terjadinya bugs akan berkurang karena sebelum kode dibuat, kita sudah memikirkan kasus-kasus yang mungkin terjadi. Jika dilakukan secara terbalik, maka kode yang kita buat memiliki risiko besar untuk terkena bugs di kemudian hari.
4. Jelaskan mengapa pada latihan no 3, main class spring tidak diikutsertakan ke dalam perhitungan coverage? Apa saja yang dapat menyebabkan suatu class dapat di-exclude dari perhitungan code coverage.
   - Main class tidak termasuk ke dalam perhitungan coverage karena class ini tidak memiliki hubungan dengan unit test yang kita buat dan class ini hanya berfungsi sebagai `runner` dari aplikasi Shapee. Suatu class dapat kita exclude dari perhitungan coverage apabila class tersebut tidak berhubungan dengan unit test yang kita buat dan tidak berhubungan dengan MVC Spring.

BEFORE TESTING IMPLEMENTATION
<a href="https://i.imgur.com/9U1ltJ3r.png"><img src="https://i.imgur.com/9U1ltJ3r.png" alt="Screen-Shot-2019-09-18-at-17-32-38" border="0"></a>

AFTER TESTING IMPLEMENTATION
<a href="https://i.imgur.com/4kE3sqmr.png"><img src="https://i.imgur.com/4kE3sqmr.png" alt="Screen-Shot-2019-09-18-at-17-32-38" border="0"></a>

### Tutorial 6

1. Apa itu postman? Apa kegunaan dari postman?

   - Postman merupakan suatu aplikasi yang berfungsi sebagai REST Client. Postman pada umumnya digunakan untuk melakukan uji coba terhadap REST API yang telah kita buat sebelumnya.
   - Postman memiliki fitur-fitur lain yaitu Sharing Collection API for Documentation, Testing API, Realtime Collaboration Team, Monitoring API, dan Integration.

2. Apa kegunaan dari annotation `@JsonIgnoreProperties`?

   - `@JsonIgnoreProperties` digunakan untuk memberikan spesifikasi list atribut dari sebuah **class** yang akan diabaikan ketika menjalankan serialization dan deserialization JSON. Apabila tidak menggunakan annotation tersebut, akan terjadi error saat menjalankan serialization dan deserialization ketika Java Object tidak memiliki field yang dimiliki oleh JSON.

   - Berikut adalah contoh penggunaan `@JsonIgnoreProperties` :

   ```
   import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

   @JsonIgnoreProperties({"firstName", "lastName"})
   public class PersonIgnoreProperties {
       public long    personId = 0;
       public String  firstName = null;
       public String  lastName  = null;
    }
   ```

   - Pada contoh diatas, kedua atribut `firstName` dan `lastName` akan diabaikan di dalam JSON serialization dan deserialization karena atribut tersebut tercantum di dalam anotasi `@JsonIgnoreProperties`.

3) Apa itu ResponseEntity dan apa kegunaannya?
   - ResponseEntity merupakan sebuaj class yang merepresentasikan HTTP Response, termasuk status code, headers, dan body secara keseluruhan. ResponseEntity dapay digunakan untuk memanipulasi HTTP Response dari request yang kita lakukan.

### Tutorial 7

1. Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode
   yang telah anda buat) konsep tersebut diimplementasi?

   - Otentikasi = Suatu proses melakukan verifikasi krendensial seseorang, biasanya dapat berupa username ataupun password. Pada tutorial kali ini, otentikasi dilakukan pada bagian WebConfig.

   ```
   public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
   ```

   - Otorisasi = Merupakan proses pengecekan yang dilakukan setelah dilakukan otentikasi, tujuannya adalah untuk memberikan akses kepada seseorang untuk melakukan manipulasi terhadap sesuatu.

   ```
   http
       .authorizeRequests()
       .antMatchers("/css/**").permitAll()
       .antMatchers("/jss/**").permitAll()
       .antMatchers("/store/**").hasAnyAuthority("MERCHANT")
       .antMatchers("/user/addUser/**").hasAnyAuthority("ADMIN")
   ```

2. Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerjanya!

   - BCryptPasswordEncoder digunakana unntuk melakukan hashing terhhadap password yang akan disimpan ke dalam database. Cara kerjanya adalahh setelah user memasukan data username dan password kemudian ketika user ingin menyimpan data tersebut maka password akan di hashing terlebih dahulu oleh BCryptPasswordEncoder. Kemudian hashing password juga akan dilakukan ketika login, password akan di enkripsi dan dilakukan pencocokan denga database.

3. Jelaskan secara singkat apa itu UUID dan mengapa kita memakai UUID di UserModel.java?

   - UUID adalah suatu hex digit yang terdiri dari 32 karakter acak. UUID digunakan dalam class UserModel dengan tujuan untuk menambah keamanan user karena user id akan generate dengan 32 karakter acak sehingga tidak mudah untuk ditebak olehh hacker.

4. Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut
   padahal kita sudah memiliki class UserRoleServiceImpl.java?
   - UserDetailServiceImpl akan mengembalikan kredensial user saat login. Class ini bertujuan untuk melakukan otorisasi terhadap hak akses user berdasarkan role yang telah ditetapkan.

### Tutorial 8

1. Jelaskan apa yang Anda lakukan di latihan dalam satu paragraf per-soal. Berikan screenshot sebagai ilustrasi dari apa yang Anda jelaskan.

   - Pada latihan <strong> pertama </strong>, saya menambahkan validasi menggunakan if/else dengan mengecek apakah checked bernilai true. Jika true, maka checkbox akan ditampilkan

   ```
   <input
         type={checked ? "checkbox" : "hidden"}
         checked={checked}
         onChange={handleChange}
   />
   ```

   - Sebelumnya sudah terdapat handleItemClick yang memberikan Item sebuah function untuk menambah dan menghapus favorit. Untuk menjalankan latihan <strong> kedua </strong> ini, saya mengubah handleItemClick menjadi handleFavClick, serta menambah 1 fungsi yang serupa yaitu handleItemClick, namun tidak diberikan akses untuk menghapus dengan menghilangkan potongan kode `else newItems.splice(targetInd, 1);`

   ```
   handleFavClick = item => {
       const newItems = [...this.state.favItems];
       const newItem = { ...item };

       const targetInd = newItems.findIndex(it => it.id === newItem.id);
       if (targetInd < 0) newItems.push(newItem);
       else newItems.splice(targetInd, 1);

       this.setState({ favItems: newItems });
   };

   handleItemClick = item => {
       const newItems = [...this.state.favItems];
       const newItem = { ...item };

       const targetInd = newItems.findIndex(it => it.id === newItem.id);
       if (targetInd < 0) newItems.push(newItem);

       this.setState({ favItems: newItems });
   };
   ```

   - Kemudian, saya mengganti onItemClick yang sebelumnya adalah `this.handleItemClick` menjadi `onItemClick={this.handleFavClick}` pada elemen My Favorit

   - Pada latihan <strong> ketiga </strong>, saya menambahkan toggle button dengan membuat state terlebih dahulu yang memiliki nilai default `false`.

   ```
   state = {
       favItems: [],
       textDisplay: false
    };
   ```

   - kemudian, saya membuat function toggleButton yang memberikan nilai boolean berkebalikan dari state yang masuk ke function ini untuk menjalankan on/off dari toggle button.

   ```
   toggleButton = () => {
       this.setState(currentState => ({
           textDisplay: !currentState.textDisplay
       }));
    };
   ```

   - setelah itu, menampilkan toggle button dengan memberikannya fungsi untuk menjalankan toggleButton()

   ```
    <label className="switch">
        <input type="checkbox" onClick={this.toggleButton}/>
        <span className="slider round"></span>
    </label>
   ```

   - apabila toggle bernilai true maka akan menampilkan favourites, namun, apabila bernilai false, tidak akan menampilkan favourites

   ```
   <div className={`col-sm \${textDisplay ? "d-block" : "d-none"}`}>
   ```

   - Pada latihan <strong> keempat </strong>, saya melakukan pengecekan apakah list bernilai 0. Apabila tidak bernilai 0, maka menampilkan list favourites. Namun jika bernilai 0, akan menampilkan empty states

   ```
   {items.length !== 0 ? (
        <div className="list-group">
            {items.map(item => (
            <Item key={item.id} item={item} onChange={onItemClick} />
            ))}
        </div>
    ) : (
    <EmptyState />
    )}
   ```

   - berikut adalah kode empty states

   ```
   import React from "react";
    export default function EmptyState() {
        return (
            <>
                <h3>Belum ada item yang dipilih</h3>
                <p>Klik salah satu item di daftar Menu/Produk</p>
            </>
        );
    }
   ```

### Tutorial 9

1. Ceritakan langkah-langkah yang kalian lakukan untuk solve LATIHAN no.1, dan mengapa kalian melakukan langkah-langkah tersebut?

   - Saya memberikan property `value` pada input di method renderForm. Kemudian dilakukan setState dengan value "" untuk nama, keterangan, dan followers setelah submit form dilakukan. Sehingga, value pada input akan selalu menjadi kosong setelah form di submit.

   ```
   constructor(props) {
   super(props);
   this.state = {
     stores: [],
     searchedStores: [],
     isCreate: false,
     isEdit: false,
     isLoading: true,
     id: "",
     nama: "",
     keterangan: "",
     followers: "",
     totalPage: "",
     currentPage: 1
   };
   }
   ```

   ```
   submitAddStoreHandler = event => {
    event.preventDefault();
    this.setState({ isLoading: true });
    this.addStore();
    this.canceledHandler();

    this.setState({
      nama: "",
      keterangan: "",
      followers: ""
    });
   };
   ```

2. Jelaskan fungsi dari async dan await!

   - `async`: Menandakan bahwa fungsi yang dibuat bersifat non-blocking (asynchronous).
   - `await`: Cara untuk menghandle proses async yang sedang berjalan. await membuat fungsi yang sedang dieksekusi harus berlangsung hingga selesai terlebih dahulu sebbelum code setelah await akan dijalankan.

3. Masukkan jawaban dari TODO pada Component Lifecycle pada pertanyaan ini

   <a href="https://i.imgur.com/8xldGn2r.png"><img src="https://i.imgur.com/8xldGn2r.png" alt="Screen-Shot-2019-09-18-at-17-32-38" border="0"></a>

   <a href="https://i.imgur.com/RsO3qxP.png"><img src="https://i.imgur.com/RsO3qxP.png" alt="Screen-Shot-2019-09-18-at-17-32-38" border="0"></a>

   <a href="https://i.imgur.com/bX2uZpsr.png"><img src="https://i.imgur.com/bX2uZpsr.png" alt="Screen-Shot-2019-09-18-at-17-32-38" border="0"></a>

   <a href="https://i.imgur.com/3OJTwqYr.png"><img src="https://i.imgur.com/3OJTwqYr.png" alt="Screen-Shot-2019-09-18-at-17-32-38" border="0"></a>

   <a href="https://i.imgur.com/WforNTAr.png"><img src="https://i.imgur.com/WforNTAr.png" alt="Screen-Shot-2019-09-18-at-17-32-38" border="0"></a>

   <a href="https://i.imgur.com/bY0dPQfr.png"><img src="https://i.imgur.com/bY0dPQfr.png" alt="Screen-Shot-2019-09-18-at-17-32-38" border="0"></a>

   <a href="https://i.imgur.com/q8EEGtYr.png"><img src="https://i.imgur.com/q8EEGtYr.png" alt="Screen-Shot-2019-09-18-at-17-32-38" border="0"></a>

4) Jelaskan fungsi dari `componentDidMount`, `shouldComponentUpdate`, `componentDidUpdate`, `componentWillReceiveProps`, `componentWillUnmount`.

   - `componentDidMount`: Fungsi ini dijalankan setelah component telah selesai dirender untuk melakukan fetching data.
   - `shouldComponentUpdate`: Fungsi ini dijalankan sebelum component melakukan render ulang terhadap perubahan yang terjadi. Apabila mengembalikan nilai true, component akan dirender ulang, dan sebaliknya, component tidak dirender ulang. Fungsi ini memiliki 2 parameter, yaitu `nextProps` dan `nextState`.
   - `componentDidUpdate`: Fungsi ini dijalankan setelah component yang di render ulang (diupdate) sudah selesai di render. componentDidUpdate digunakan saat melakukan interaksi dengan environment non-React seperti HTTP Request. Fungsi ini menerima 2 parameter, yaitu `prevProps` dan `prevState`.
   - `componentWillReceiveProps`: Fungsi ini dijalankan sebelum component melakukan apa saja terhadap props yang baru mengalami pembaruan (update). Dengan menggunakan metode ini, kita dapat membandingkan props yang ada dengan yang baru dan memeriksa apakah terdapat perubahan terhadap props tersebut.
   - `componentWillUnmount`: Fungsi ini dijalankan sebelum component di hapus dari DOM. Biasanya pada lifecycle ini, kita akan melakukan cleanup seperti membatalkan network request dan menghilangkan event listener.
