package com.sspu.library;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MenuImageAdapter extends BaseAdapter {	// �̳�BaseAdapter
	private Context context; 						// ���������Ķ���
	private ImageView[] menuImg; 					// �������б�ǩ��ͼƬ��ʾ
	private int[] selectedMenuImg; 					// ����ѡ�е�ImageView����
	private static int[] imgIds1; 
	
	/**
	 * ����һ��MenuImageAdapter���ʵ��
	 * @param context �����Ķ���
	 * @param imgIds ���е�ͼƬ��Դ��ID����
	 * @param width ImageView����Ŀ��
	 * @param height ImageView�������ʾ�߶�
	 * @param selectedMenuImg ѡ�е�ImageView��ID
	 */
	public MenuImageAdapter(Context context, int imgIds[], int width,
			int height, int selectedMenuImg[]) {		// ������ղ���
		this.context = context; 					// ����Context����
		imgIds1 = imgIds;
		this.selectedMenuImg = selectedMenuImg; 	// ����ѡ�е�ID
		this.menuImg = new ImageView[imgIds.length]; // ʵ����ImageView����
		for (int x = 0; x < imgIds.length; x++) { 	// ʵ����ÿһ��ImageView
			this.menuImg[x] = new ImageView(this.context); // ʵ����ImageView����
			this.menuImg[x].setLayoutParams(new GridView.LayoutParams(width,
					height));						// ����ͼƬ�Ĳ��ֲ���
			this.menuImg[x].setAdjustViewBounds(false); // �������߽�
			this.menuImg[x].setPadding(4, 4, 4, 4); 	// ���ñ߾�
			this.menuImg[x].setImageResource(imgIds[x]); // ������ʾͼƬ
		}

	}

	@Override
	public int getCount() {								// ����ȫ�����ݸ���
		return this.menuImg.length;
	}

	@Override
	public Object getItem(int position) {				// ȡ��ָ��λ�õĶ���
		return this.menuImg[position];
	}

	@Override
	public long getItemId(int position) {				// ȡ�ö����ID
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imgView = null;
		if (convertView == null) {						// �ж��Ƿ����ת������ͼ
			imgView = this.menuImg[position];			// ȡ�����е���ͼ
		} else {
			imgView = (ImageView) convertView;			// ȡ�����е���ͼ
		}
		return imgView;									// ������ͼ
	}

	public void setFocus(int selId) {					// ѡ��ѡ��ʱ����
		for (int x = 0; x < this.menuImg.length; x++) {
			if (x != selId) { 							// ���ǵ�ǰѡ����
				this.menuImg[x].setBackgroundResource(0);	// ȡ������ͼƬ
				this.menuImg[x].setImageResource(imgIds1[x]); // ������ʾͼƬ
			}
			else{
				this.menuImg[selId].setBackgroundResource(this.selectedMenuImg[selId]);// ���ñ���
				this.menuImg[selId].setImageResource(0); // ������ʾͼƬ
				}
		}
		
	}
}
