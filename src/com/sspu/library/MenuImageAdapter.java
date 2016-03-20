package com.sspu.library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MenuImageAdapter extends BaseAdapter {	// 继承BaseAdapter
	private Context context; 						// 传递上下文对象
	private ImageView[] menuImg; 					// 保存所有标签的图片显示
	private int[] selectedMenuImg; 					// 保存选中的ImageView索引
	private static int[] imgIds1; 
	
	/**
	 * 创建一个MenuImageAdapter类的实例
	 * @param context 上下文对象
	 * @param imgIds 所有的图片资源的ID集合
	 * @param width ImageView组件的宽度
	 * @param height ImageView组件的显示高度
	 * @param selectedMenuImg 选中的ImageView的ID
	 */
	public MenuImageAdapter(Context context, int imgIds[], int width,
			int height, int selectedMenuImg[]) {		// 构造接收参数
		this.context = context; 					// 接收Context对象
		imgIds1 = imgIds;
		this.selectedMenuImg = selectedMenuImg; 	// 接收选中的ID
		this.menuImg = new ImageView[imgIds.length]; // 实例化ImageView数组
		for (int x = 0; x < imgIds.length; x++) { 	// 实例化每一个ImageView
			this.menuImg[x] = new ImageView(this.context); // 实例化ImageView对象
			this.menuImg[x].setLayoutParams(new GridView.LayoutParams(width,
					height));						// 定义图片的布局参数
			this.menuImg[x].setAdjustViewBounds(false); // 不调整边界
			this.menuImg[x].setPadding(4, 4, 4, 4); 	// 设置边距
			this.menuImg[x].setImageResource(imgIds[x]); // 设置显示图片
		}

	}

	@Override
	public int getCount() {								// 返回全部数据个数
		return this.menuImg.length;
	}

	@Override
	public Object getItem(int position) {				// 取得指定位置的对象
		return this.menuImg[position];
	}

	@Override
	public long getItemId(int position) {				// 取得对象的ID
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imgView = null;
		if (convertView == null) {						// 判断是否存在转换的视图
			imgView = this.menuImg[position];			// 取得已有的视图
		} else {
			imgView = (ImageView) convertView;			// 取得已有的视图
		}
		return imgView;									// 返回视图
	}

	public void setFocus(int selId) {					// 选中选项时触发
		for (int x = 0; x < this.menuImg.length; x++) {
			if (x != selId) { 							// 不是当前选中项
				this.menuImg[x].setBackgroundResource(0);	// 取消背景图片
				this.menuImg[x].setImageResource(imgIds1[x]); // 设置显示图片
			}
			else{
				this.menuImg[selId].setBackgroundResource(this.selectedMenuImg[selId]);// 设置背景
				this.menuImg[selId].setImageResource(0); // 设置显示图片
				}
		}
		
	}
}
