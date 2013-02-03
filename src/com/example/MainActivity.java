package com.example;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity implements LocationListener {

	Button btn;
	int latitude = 34159000;
	LocationManager locationManager;
	int longitude = 73220000;
	MapView mapView;
	MapController mc;
	String provider;

	// на него вешаем клик например кнопку на вью создали, и по нажатию находим нас
	public void myCurrentLocation() {

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		// проверка на пустоту провайдера
		if ((provider != null) && !provider.equals("")) {
			// определяем местоположение
			Location location = locationManager.getLastKnownLocation(provider);
			locationManager.requestLocationUpdates(provider, 20000, 1, this);
			if (location != null) {
				// задаем координаты
				GeoPoint myLication = new GeoPoint(latitude, longitude);
				// перемещаемся туда при помощи данной функции mc - MapController
				// mc.animateTo(myLication);
				// рисуем картинку на экране
				Drawable makerDefault = getResources().getDrawable(R.drawable.ic_launcher);
				// выводим маркер на карте
				MirItemizedOverlay itemizedOverlay = new MirItemizedOverlay(makerDefault);
				// задаем координаты маркеру
				itemizedOverlay.addOverlayItem(latitude, longitude, "My Location");
				// выводи картинку по заданным координатам
				mapView.getOverlays().add(itemizedOverlay);
				// перемещаемся к центру экрана
				mc.setCenter(new GeoPoint(latitude, longitude));
			} else {
				Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Displaying Zooming controls
		mapView = (MapView) findViewById(R.id.mapView); // определяем карту
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true); // Satellite View
		// mapView.setStreetView(true); // Street View
		// mapView.setTraffic(true); // Traffic View
		// задаем координаты
		GeoPoint myLication = new GeoPoint(latitude, longitude); // перемещаемся туда при помощи
		// данной функции, mc - объект
		// типа MapController
		Drawable makerDefault = getResources().getDrawable(R.drawable.ic_launcher); // выводим маркер на
		// карте
		MirItemizedOverlay itemizedOverlay = new MirItemizedOverlay(makerDefault); // задаем координаты
		// маркеру
		itemizedOverlay.addOverlayItem(latitude, longitude, "My Location");
		// выводи картинку по заданным координатам
		mapView.getOverlays().add(itemizedOverlay);
		// перемещаемся к центру экрана
		btn = (Button) findViewById(R.id.btn);
		// ищем нас
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				myCurrentLocation();
			}
		});
	}

	@Override
	public void onLocationChanged(Location location) {

		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderDisabled(String provider) {

		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {

		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isRouteDisplayed() {

		return false;
	}
}