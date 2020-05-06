package ua.khnu.service;

import ua.khnu.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public List<List<Product>> getRandomForMain() {
        List<Product> toys=new ArrayList<>();
        List<Product> statuettes=new ArrayList<>();
        List<Product> tshirts=new ArrayList<>();
        List<Product> comics=new ArrayList<>();
        for(int i=0;i<4;i++){
            Toy toy=new Toy();
            toy.setColor(i+"toy");
            toy.setImage(i+"toyImg");
            toy.setId(i);
            toy.setTitle(i+"toy");
            toys.add(toy);
            Tshirt tshirt=new Tshirt();
            tshirt.setColor(i+"tshirt");
            tshirt.setImage(i+"tshirtImg");
            tshirt.setId(i);
            tshirt.setTitle(i+"tshirt");
            tshirts.add(tshirt);
            Statuette statuets=new Statuette();
            statuets.setColor(i+"statuets");
            statuets.setImage(i+"Img");
            statuets.setId(i);
            statuets.setTitle(i+"statuets");
            statuettes.add(statuets);
            Comics comi=new Comics();
            comi.setColor(i+"comi");
            comi.setImage(i+"Img");
            comi.setId(i);
            comi.setTitle(i+"comi");
            comics.add(comi);

        }
        List<List<Product>> res=new ArrayList<>();
        res.add(comics);
        res.add(statuettes);
        res.add(tshirts);
        res.add(toys);

        return res;
    }
}
