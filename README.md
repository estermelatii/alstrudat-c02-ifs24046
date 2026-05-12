# alstrudat-c??-ifs24046

## Description

Diberikan sebuah sistem manajemen inventaris toko yang menggunakan **Hash Table dengan metode Double Hashing** untuk menyimpan data produk.

Setiap produk memiliki:
- **ID Produk** (integer, sebagai key)
- **Nama Produk** (string)
- **Stok** (integer)

Hash Table memiliki ukuran **13** (bilangan prima).

**Fungsi Hash Utama:**
```
h1(key) = key % 13
```

**Fungsi Hash Kedua (untuk Double Hashing):**
```
h2(key) = 7 - (key % 7)
```

**Probe sequence (Double Hashing):**
```
probe(i) = (h1(key) + i * h2(key)) % 13
```

Kamu akan menerima sejumlah operasi berikut:

| Operasi | Format Input | Keterangan |
|---------|-------------|------------|
| INSERT  | `INSERT id nama stok` | Masukkan produk ke hash table. Jika ID sudah ada, update stoknya. |
| DELETE  | `DELETE id` | Hapus produk (lazy deletion — tandai sebagai DELETED). |
| SEARCH  | `SEARCH id` | Cari produk berdasarkan ID. |
| UPDATE  | `UPDATE id tambah_stok` | Tambahkan stok produk yang ada. |
| COUNT   | `COUNT` | Hitung jumlah produk aktif dalam hash table. |
| DISPLAY | `DISPLAY` | Tampilkan isi hash table dari index 0–12. |

**Format Input:**
- Baris pertama: jumlah operasi `N`
- `N` baris berikutnya: operasi-operasi di atas

**Format Output:**
- `INSERT id`: cetak `INSERT id -> index`
  - Jika collision terjadi: cetak `COLLISION at index probe_ke` (untuk setiap probe yang gagal)
  - Jika tabel penuh: cetak `TABLE FULL`
- `DELETE id`: cetak `DELETE id -> index` atau `DELETE id -> NOT FOUND`
- `SEARCH id`: cetak `FOUND id nama stok at index` atau `NOT FOUND id`
- `UPDATE id tambah_stok`: cetak `UPDATE id stok_lama -> stok_baru` atau `UPDATE id -> NOT FOUND`
- `COUNT`: cetak `COUNT = n`
- `DISPLAY`: cetak setiap baris dalam format `[index] id nama stok` atau `[index] EMPTY` atau `[index] DELETED`

**Catatan penting:**
- Saat INSERT, jika slot bertanda DELETED, boleh dipakai (reuse).
- Saat SEARCH/DELETE/UPDATE, slot DELETED tetap dilalui (tidak menghentikan pencarian).
- Saat INSERT dan key yang sama sudah ada (aktif), lakukan UPDATE stok saja (cetak `UPDATE id stok_lama -> stok_baru`).

## Source Codes

| No | File | Deskripsi |
|----|------|-----------|
| 1  | App.java | Entry point, membaca input dan memanggil operasi |
| 2  | Program.java | Implementasi Hash Table Double Hashing |

## Test Cases

| No | Input | Output |
|----|-------|--------|
| 1  | Lihat testcases/input1.txt | Lihat testcases/expected1.txt |
| 2  | Lihat testcases/input2.txt | Lihat testcases/expected2.txt |
| 3  | Lihat testcases/input3.txt | Lihat testcases/expected3.txt |
| 4  | Lihat testcases/input4.txt | Lihat testcases/expected4.txt |
| 5  | Lihat testcases/input5.txt | Lihat testcases/expected5.txt |
| 6  | Lihat testcases/input6.txt | Lihat testcases/expected6.txt |
| 7  | Lihat testcases/input7.txt | Lihat testcases/expected7.txt |
| 8  | Lihat testcases/input8.txt | Lihat testcases/expected8.txt |
| 9  | Lihat testcases/input9.txt | Lihat testcases/expected9.txt |
| 10 | Lihat testcases/input10.txt | Lihat testcases/expected10.txt |

## Compile

```
mvn clean package
```

## Run

```
java -cp target/alstrudat-c??-ifs24046-1.0-SNAPSHOT.jar del.alstrudat.App < testcases/input1.txt
```
