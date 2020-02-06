public class Restaurant {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FoodFactory ff=new FoodFactory();
		ff.produceTV("RouSiHeQingJiao").play();
	}

}

abstract class Food {
	public abstract void play();
}

class XiHongShiChaoJiDan extends Food {
	public void play() {
		System.out.println("西红柿炒鸡蛋");
	}
}

class JiDanChaoXiHongShi extends Food {
	public void play() {
		System.out.println("鸡蛋炒西红柿");
	}
}
class QingJiaoRouSi extends Food {
	public void play() {
		System.out.println("青椒肉丝");
	}
}

class RouSiQingJiao extends Food {
	public void play() {
		System.out.println("肉丝青椒");
	}
}

class FoodFactory {
	public static Food produceTV(String caiLiao) {
		if (caiLiao.equalsIgnoreCase("XiHongShiHeJiDan")) {
			return new XiHongShiChaoJiDan();
		} else if (caiLiao.equalsIgnoreCase("JiDanHeXiHongShi")) {
			return new JiDanChaoXiHongShi();
		} 
		else if (caiLiao.equalsIgnoreCase("RouSiHeQingJiao")) {
			return new RouSiQingJiao();
		}
		else if (caiLiao.equalsIgnoreCase("QingJiaoHeRouSi")) {
			return new QingJiaoRouSi();
		}
		return null;
	}
}
