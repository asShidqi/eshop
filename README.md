### Refleksi 1
Dalam mengimplementasikan fitur edit dan delete pada Spring Boot, saya telah menerapkan prinsip clean code seperti penggunaan nama variabel dan metode yang jelas, serta pemisahan logika bisnis ke dalam layer service. Selain itu, saya memastikan secure coding dengan validasi input di controller dan penggunaan repository hanya melalui service layer untuk mencegah akses langsung ke data.

Namun, ada beberapa perbaikan yang dapat dilakukan, seperti menambahkan exception handling yang lebih spesifik untuk menangani error saat produk tidak ditemukan.

### Refleksi 2
1. Setelah menulis unit test, saya merasa lebih yakin bahwa fitur yang dikembangkan berjalan sesuai harapan. Namun, jumlah unit test dalam satu kelas sebaiknya disesuaikan dengan kompleksitas logika bisnisnya. Untuk memastikan cakupan pengujian yang cukup, saya memanfaatkan metrik code coverage. Meskipun code coverage mencapai 100%, bukan berarti kode bebas dari bug, karena masih ada kemungkinan kesalahan logika yang tidak terdeteksi oleh tes.

2. Saat membuat functional test suite baru, saya menyadari adanya duplikasi kode dari pengujian sebelumnya. Hal ini dapat mengurangi kualitas kode dan menyulitkan pemeliharaan. Untuk memperbaikinya, saya dapat mengabstraksi setup dan variabel yang sama ke dalam superclass atau menggunakan utility class agar kode lebih modular dan terorganisir.