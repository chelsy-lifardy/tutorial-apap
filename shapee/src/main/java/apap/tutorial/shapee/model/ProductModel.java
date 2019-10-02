package apap.tutorial.shapee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;


@Entity
@Table(name="product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name="nama", nullable=false)
    private  String nama;

    @NotNull
    @Column(name="harga", nullable=false)
    private BigInteger harga;

    @NotNull
    @Column(name="stok", nullable=false)
    private BigInteger stok;

    @NotNull
    @Size(max = 50)
    @Column(name="deskripsi", nullable=false)
    private String deskripsi;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="storeid", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private StoreModel storeModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public BigInteger getHarga() {
        return harga;
    }

    public void setHarga(BigInteger harga) {
        this.harga = harga;
    }

    public BigInteger getStok() {
        return stok;
    }

    public void setStok(BigInteger stok) {
        this.stok = stok;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public StoreModel getStoreModel() {
        return storeModel;
    }

    public void setStoreModel(StoreModel storeModel) {
        this.storeModel = storeModel;
    }
}
