package apap.tutorial.shapee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "store")
public class StoreModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 10)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 30)
    @Column(name = "keterangan", nullable = false)
    private String keterangan;

    @NotNull
    @Column(name = "followers")
    private Integer followers;

    @OneToMany(mappedBy = "storeModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ProductModel> listProduct;

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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public List<ProductModel> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductModel> listProduct) {
        this.listProduct = listProduct;
    }
}