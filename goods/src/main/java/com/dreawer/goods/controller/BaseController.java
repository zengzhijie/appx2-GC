package com.dreawer.goods.controller;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import com.dreawer.goods.domain.GoodsPropertyName;
import com.dreawer.goods.domain.Sku;

/**
 * <code>BaseController</code> 它是本系统中所有控制器的基类，提供控制器通用方法的实现。
 * @author kael
 */
public class BaseController{

    // --------------------------------------------------------------------------------
    // 其他
    // --------------------------------------------------------------------------------
    
    /**
     * 获取当前系统时间。
     * @return 当前系统时间。
     * @author kael
     */
    protected Timestamp getNow() {
        return new Timestamp(System.currentTimeMillis());
    }
	
    /**
     * 生成UUID。
     * @return 32位UUID。
     * @author kael
     */
    protected String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

	/**
	 * 对SKU进行排序（售价由低到高）。
	 * @param skus 待排序的SKU列表。
	 * @return 排序后的SKU列表。
	 * @author Kael
	 * @since 2.0
	 */
	protected List<Sku> sortSkuByPriceAsc(List<Sku> skus){
		Collections.sort(skus, new Comparator<Sku>(){
			@Override
			public int compare(Sku sku1, Sku sku2) {
				return sku1.getPrice().compareTo(sku2.getPrice());
			}
		});
		return skus;
	}
	
	/**
	 * 对商品SKU进行排序（原价由低到高）。
	 * @param skus 待排序的SKU对象集合。
	 * @return 排序后的SKU对象集合。
	 * @author Kael
	 * @since 2.0
	 */
	protected List<Sku> sortSkuByOriginalPriceAsc(List<Sku> skus){
		Collections.sort(skus, new Comparator<Sku>(){
			@Override
			public int compare(Sku sku1, Sku sku2) {
				return sku1.getOriginalPrice().compareTo(sku2.getOriginalPrice());
			}
		});
		return skus;
	}
    
	/**
	 * 对SKU描述进行排序（排列序号由低到高低到高）。
	 * @param skuDescription 待排序的SKU描述。
	 * @return 排序后的SKU描述。
	 * @author Kael
	 * @since 2.0
	 */
	protected List<String> sortSkuDescription(List<String> skuDescription, List<GoodsPropertyName> goodsPropertyNames){
		Collections.sort(skuDescription, new Comparator<String>(){
			@Override
			public int compare(String desc1, String desc2) {
				
				Integer desc1Squence = 0;
				Integer desc2Squence = 0;
				
				for (GoodsPropertyName goodsPropertyName : goodsPropertyNames) {
					if(desc1.contains(goodsPropertyName.getPropertyNameId())){
						desc1Squence = goodsPropertyName.getSquence();
					}
					if(desc2.contains(goodsPropertyName.getPropertyNameId())){
						desc2Squence = goodsPropertyName.getSquence();
					}
				}
				
				return desc1Squence.compareTo(desc2Squence);
			}
		});
		return skuDescription;
	}
	
/*	@Test
	public void test13213() throws IOException{
		String ids = "beddb500503b11e8a3bc7cd30abc,"+
				"bef8eff0503b11e8a3bc7cd30abc,"+
				"bf03a16d503b11e8a3bc7cd30abc,"+
				"bf06a968503b11e8a3bc7cd30abc,"+
				"bf0a40ed503b11e8a3bc7cd30abc,"+
				"bf0cc525503b11e8a3bc7cd30abc,"+
				"bf0f9415503b11e8a3bc7cd30abc,"+
				"bf11e4c0503b11e8a3bc7cd30abc,"+
				"bf13c734503b11e8a3bc7cd30abc,"+
				"bf16b54a503b11e8a3bc7cd30abc,"+
				"bf1e2946503b11e8a3bc7cd30abc,"+
				"bf240f31503b11e8a3bc7cd30abc,"+
				"bf28a699503b11e8a3bc7cd30abc,"+
				"bf2aca9c503b11e8a3bc7cd30abc,"+
				"bf38333f503b11e8a3bc7cd30abc,"+
				"bf3aa421503b11e8a3bc7cd30abc,"+
				"bf47e5f9503b11e8a3bc7cd30abc,"+
				"bf4c467f503b11e8a3bc7cd30abc,"+
				"bf4ea2b4503b11e8a3bc7cd30abc,"+
				"bf4ef91c503b11e8a3bc7cd30abc,"+
				"bf50f833503b11e8a3bc7cd30abc,"+
				"bf5cd1df503b11e8a3bc7cd30abc,"+
				"bf63f695503b11e8a3bc7cd30abc,"+
				"bf727798503b11e8a3bc7cd30abc,"+
				"bf7e28f5503b11e8a3bc7cd30abc,"+
				"bf7f6fe9503b11e8a3bc7cd30abc,"+
				"bf7fc46d503b11e8a3bc7cd30abc,"+
				"bf83960f503b11e8a3bc7cd30abc,"+
				"bf8e37a7503b11e8a3bc7cd30abc,"+
				"bf92bacf503b11e8a3bc7cd30abc,"+
				"bf99cec1503b11e8a3bc7cd30abc,"+
				"bf9e0b75503b11e8a3bc7cd30abc,"+
				"bfa46890503b11e8a3bc7cd30abc,"+
				"bfa5c61b503b11e8a3bc7cd30abc,"+
				"bfb1d95c503b11e8a3bc7cd30abc,"+
				"bfc08a9c503b11e8a3bc7cd30abc,"+
				"bfc4b7a2503b11e8a3bc7cd30abc,"+
				"bfcaa6cc503b11e8a3bc7cd30abc,"+
				"bfcc1c76503b11e8a3bc7cd30abc,"+
				"bfcc76c7503b11e8a3bc7cd30abc,"+
				"bfd794ee503b11e8a3bc7cd30abc,"+
				"bfe34c84503b11e8a3bc7cd30abc,"+
				"bfed75d7503b11e8a3bc7cd30abc,"+
				"bff92e95503b11e8a3bc7cd30abc,"+
				"bffbfa0a503b11e8a3bc7cd30abc,"+
				"bffd02e3503b11e8a3bc7cd30abc,"+
				"bfff572b503b11e8a3bc7cd30abc,"+
				"c0034541503b11e8a3bc7cd30abc,"+
				"c005d093503b11e8a3bc7cd30abc,"+
				"c008efe6503b11e8a3bc7cd30abc,"+
				"c018f7e3503b11e8a3bc7cd30abc,"+
				"c01e3ffa503b11e8a3bc7cd30abc,"+
				"c01f886c503b11e8a3bc7cd30abc,"+
				"c0220336503b11e8a3bc7cd30abc,"+
				"c024aa09503b11e8a3bc7cd30abc,"+
				"c02c007e503b11e8a3bc7cd30abc,"+
				"c02dc022503b11e8a3bc7cd30abc,"+
				"c037e8a6503b11e8a3bc7cd30abc,"+
				"c03a4768503b11e8a3bc7cd30abc,"+
				"c03c621f503b11e8a3bc7cd30abc,"+
				"c03e78eb503b11e8a3bc7cd30abc,"+
				"c03ede4c503b11e8a3bc7cd30abc,"+
				"c04575a1503b11e8a3bc7cd30abc,"+
				"c0508e24503b11e8a3bc7cd30abc,"+
				"c0624f6a503b11e8a3bc7cd30abc,"+
				"c0654255503b11e8a3bc7cd30abc,"+
				"c073e834503b11e8a3bc7cd30abc,"+
				"c07584ec503b11e8a3bc7cd30abc,"+
				"c077bba9503b11e8a3bc7cd30abc,"+
				"c07bda3d503b11e8a3bc7cd30abc,"+
				"c08386e9503b11e8a3bc7cd30abc,"+
				"c08d9d51503b11e8a3bc7cd30abc,"+
				"c096f266503b11e8a3bc7cd30abc,"+
				"c09c07d6503b11e8a3bc7cd30abc,"+
				"c0bfb437503b11e8a3bc7cd30abc,"+
				"c0d66d6a503b11e8a3bc7cd30abc,"+
				"c0dac4e9503b11e8a3bc7cd30abc,"+
				"c0ed8884503b11e8a3bc7cd30abc,"+
				"c1019f93503b11e8a3bc7cd30abc,"+
				"c101f052503b11e8a3bc7cd30abc,"+
				"c10cb8d0503b11e8a3bc7cd30abc,"+
				"c112b29f503b11e8a3bc7cd30abc,"+
				"c139bd10503b11e8a3bc7cd30abc,"+
				"c143693c503b11e8a3bc7cd30abc,"+
				"c146becd503b11e8a3bc7cd30abc,"+
				"c152553e503b11e8a3bc7cd30abc,"+
				"c16fc22a503b11e8a3bc7cd30abc,"+
				"c175a1f5503b11e8a3bc7cd30abc,"+
				"c1823335503b11e8a3bc7cd30abc,"+
				"c1a0c6c8503b11e8a3bc7cd30abc,"+
				"c1a76e64503b11e8a3bc7cd30abc,"+
				"c1a7c38c503b11e8a3bc7cd30abc,"+
				"c1a810f8503b11e8a3bc7cd30abc,"+
				"c1c39aad503b11e8a3bc7cd30abc,"+
				"c1c62dea503b11e8a3bc7cd30abc,"+
				"c1c73591503b11e8a3bc7cd30abc,"+
				"c1e7ffaf503b11e8a3bc7cd30abc,"+
				"c1f7e511503b11e8a3bc7cd30abc,"+
				"c2623dde503b11e8a3bc7cd30abc,"+
				"c282c999503b11e8a3bc7cd30abc,"+
				"c299cbfb503b11e8a3bc7cd30abc,"+
				"c2c40142503b11e8a3bc7cd30abc,"+
				"c2f4556b503b11e8a3bc7cd30abc,"+
				"c311a28f503b11e8a3bc7cd30abc,"+
				"c31547d3503b11e8a3bc7cd30abc,"+
				"c31b962b503b11e8a3bc7cd30abc,"+
				"c320b968503b11e8a3bc7cd30abc,"+
				"c3310f60503b11e8a3bc7cd30abc,"+
				"c342e1fa503b11e8a3bc7cd30abc,"+
				"c378a2ff503b11e8a3bc7cd30abc,"+
				"c380ec84503b11e8a3bc7cd30abc,"+
				"c388c818503b11e8a3bc7cd30abc,"+
				"c3911338503b11e8a3bc7cd30abc,"+
				"c3bd39e3503b11e8a3bc7cd30abc,"+
				"c3d6df38503b11e8a3bc7cd30abc,"+
				"c3f7335d503b11e8a3bc7cd30abc,"+
				"c4038f6c503b11e8a3bc7cd30abc,"+
				"c40dfbf8503b11e8a3bc7cd30abc,"+
				"c4389bda503b11e8a3bc7cd30abc,"+
				"c4852474503b11e8a3bc7cd30abc,"+
				"c4bebe38503b11e8a3bc7cd30abc,"+
				"c4e09d0b503b11e8a3bc7cd30abc,"+
				"c4effb01503b11e8a3bc7cd30abc,"+
				"c4f826d9503b11e8a3bc7cd30abc,"+
				"c52a1480503b11e8a3bc7cd30abc,"+
				"c5ec56c4503b11e8a3bc7cd30abc,"+
				"c5f24295503b11e8a3bc7cd30abc,"+
				"c6073849503b11e8a3bc7cd30abc,"+
				"c65bbe51503b11e8a3bc7cd30abc,"+
				"c6d99355503b11e8a3bc7cd30abc,"+
				"c71ef873503b11e8a3bc7cd30abc,"+
				"c76035bb503b11e8a3bc7cd30abc,"+
				"c78a0e89503b11e8a3bc7cd30abc,"+
				"c82b2a94503b11e8a3bc7cd30abc,"+
				"c8eee2c5503b11e8a3bc7cd30abc,"+
				"c9340eb9503b11e8a3bc7cd30abc,"+
				"c967c67a503b11e8a3bc7cd30abc,"+
				"c9d33f85503b11e8a3bc7cd30abc,"+
				"caa23a95503b11e8a3bc7cd30abc,"+
				"cb1575be503b11e8a3bc7cd30abc,"+
				"cc051d00503b11e8a3bc7cd30abc,"+
				"cd10d9b9503b11e8a3bc7cd30abc,"+
				"cd708217503b11e8a3bc7cd30abc,"+
				"cf2586a3503b11e8a3bc7cd30abc,"+
				"cf90969b503b11e8a3bc7cd30abc,"+
				"d5d313b2503b11e8a3bc7cd30abc,"+
				"d926619f503b11e8a3bc7cd30abc,"+
				"e23e781f503b11e8a3bc7cd30abc,"+
				"f181185d503b11e8a3bc7cd30abc";
		String[] split = ids.split(",");
		
		

        FileOutputStream fos = new FileOutputStream("D:\\abc.txt");
        
		for (String string : split) {
			String a = "insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b2503c11e8a3bc7cd30ab1','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db3r2503c11e8a3bc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db332503c11e8a3bc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b25034r1e8a3bc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b2503c3218a3bc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b2503c11e8a5457cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b2503c11e8a3bcact30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b9i03c11e8a3bc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b23v3c11e8a3bc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b2503cfte8a3bc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b2503b11e8a3bc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b2503q11e8a3bc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db0t2503c11e8a3bc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b2503c11e8azdc7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b2503c11e8a30o7cd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n"+
"insert `ctgr_ctg_sys_pro_val`(`ctg_id`,`pro_nam_id`,`pro_val_id`,`crer_id`,`cre_tim`) values('bf10ad94503b11e8a3bc7cd30abc','88a92efec34a4cce8550fcf7dccb3983','5d1db9b2503c11e8a3bcccd30abc','f227a37f4bdf47e7a989ed4cc48775ce','2018-08-11 02:15:42');\r\n";
			String replaceAll = a.replaceAll("bf10ad94503b11e8a3bc7cd30abc", string);
			fos.write(replaceAll.getBytes());
		}
		
		fos.close();
	}*/
	
/*	@Test
	public void test312321(){
		Pattern pricePattern = Pattern.compile("(^[0-9]\\d{0,7}$)|(^0\\.\\d{2}$)|(^[1-9]\\d{0,7}\\.\\d{2}$)");
		String originalPrice = "0";
		boolean matches = pricePattern.matcher(originalPrice.toString()).matches();
		if(matches){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}*/
	
}
