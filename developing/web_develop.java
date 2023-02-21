package web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class tmp$main {

    public static void main(String[] args){
        //a: [케이스] 쿨러 높이, span: [쿨러] 높이

        String[] list = {"m60", "dlx21", "GH4-LETO", "아이구주 hatch1 야인 화이트(미니타워)"};
        for(String a : list){
            System.out.println(a+" "+new tmp$main().getHeightByID(a, "CPU쿨러", "a"));
        }


        String[] list_2 = {"PCCOOLER PALADIN 400 (BLACK)", "PCCOOLER G6", "DEEPCOOL LS720 ARGB", "잘만 CNPS9X PERFORMA",
                "Thermalright Peerless Assassin 120 SE WHITE ARGB 서린"};
        for(String a : list_2){
            System.out.println(a+" "+new tmp$main().getHeightByID(a, "높이", "span"));
        }
    }

    public Integer getHeightByID(String input, String Key, String finalKey){
        String hrefLink;
        Integer Height = 0;
        try{
            hrefLink = Jsoup.connect("https://search.danawa.com/dsearch.php?k1="+input+"&module=goods&act=dispMain").get()
                    .select("#productListArea > div.main_prodlist.main_prodlist_list > ul > li > div.prod_main_info > div.prod_info > p.prod_name > a ")
                    .attr("href");
            Elements elementsList = Jsoup.connect(hrefLink).get()
                    .select("#blog_content > div.summary_info > div.top_summary > div > div.sub_dsc > div > dl > dd > div > div > "+finalKey);
            int tempCount = 0;
            for(Element element : elementsList){
                tempCount ++;
                if(element.text().contains(Key)){
                    Height = Integer.parseInt(elementsList.get(tempCount).text()
                            .replaceAll("mm", ""));

                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return Height;
    }


}
