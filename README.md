# Pemesanan Tiket Bioskop Dengan Poster

Aplikasi desktop ini dirancang untuk memesan tiket bioskop. Aplikasi ini memungkinkan pengguna untuk memilih film, jadwal, jumlah tiket, menampilkan total harga, menambah poster film, dan melihat atau menghapus daftar pesanan.

## Fitur Utama
1. Pilih film dan jadwal yang tersedia.
2. Masukkan jumlah tiket yang ingin dipesan.
3. Hitung total harga secara otomatis.
4. Tambahkan poster film untuk setiap film.
5. Tampilkan daftar pesanan yang sudah dibuat.
6. Hapus semua pesanan dengan mudah.

## Teknologi yang Digunakan
- *Bahasa Pemrograman*: Java
- *Framework GUI*: Swing
- *IDE*: IntelliJ IDEA

## Cara Menjalankan Aplikasi
1. Clone atau unduh repository proyek ini ke komputer Anda.
2. Buka proyek di IntelliJ IDEA.
3. Jalankan file PemesananTiketBioskopWithPoster.java melalui tombol *Run* di IntelliJ IDEA.

## Screenshot
![Contoh Poster](https://via.placeholder.com/300x450.png?text=Poster+Film)  
Contoh tampilan poster saat ditambahkan

## Struktur Kode
### *Kelas Utama*
- PemesananTiketBioskopWithPoster: Mengatur seluruh logika aplikasi.

### *Komponen GUI*
- *JComboBox*: Untuk memilih film dan jadwal.
- *JSpinner*: Untuk memasukkan jumlah tiket.
- *JLabel*: Menampilkan total harga dan poster film.
- *JButton*: Untuk menambahkan poster, memesan tiket, menampilkan daftar pesanan, dan menghapus pesanan.

### *Metode Penting*
1. hitungTotalHarga(): Menghitung total harga tiket berdasarkan jumlah tiket.
2. pesanTiket(ActionEvent e): Menambahkan pesanan ke dalam daftar.
3. tampilkanPesanan(ActionEvent e): Menampilkan daftar semua pesanan.
4. hapusPesanan(ActionEvent e): Menghapus semua pesanan.
5. tambahPoster(ActionEvent e): Menambahkan poster untuk film yang dipilih.
6. updatePoster(ActionEvent e): Memperbarui poster berdasarkan film yang dipilih.

## Catatan
- Aplikasi mendukung hingga 3 film. Anda dapat menambahkan lebih banyak film dengan memperbarui array daftarFilm dan posterPaths.
- Format gambar yang didukung untuk poster adalah *jpg, **png, dan **jpeg*.

## Lisensi
Proyek ini dilisensikan di bawah [MIT License](https://opensource.org/licenses/MIT).