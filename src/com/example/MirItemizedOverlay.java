package com.example;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;
import java.util.List;

/** @author Gleb Kravchenko
 * 
 *         Made by sloboda-studio.com in 2012 */
class MirItemizedOverlay extends ItemizedOverlay {

	private final Drawable marker;
	private final List<OverlayItem> mOverlays = new ArrayList();
	Context c;
	MainActivity flyInThe;

	public MirItemizedOverlay(Drawable defaultMarker) {

		super(boundCenterBottom(defaultMarker));
		marker = defaultMarker;
		// create locations of interest
		// GeoPoint myPlace = new GeoPoint(LatitudeE6, LongitudeE6);
		// mOverlays.add(new OverlayItem(myPlace, "My Place", "My Place"));
		populate();
	}

	public void addOverlayItem(int lat, int lon, String title) {

		GeoPoint point = new GeoPoint(lat, lon);
		OverlayItem overlayItem = new OverlayItem(point, title, null);
		addOverlayItem(overlayItem);
	}

	public void addOverlayItem(OverlayItem overlayItem) {

		mOverlays.add(overlayItem);
		populate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {

		return false;
	}

	@Override
	public int size() {

		return mOverlays.size();
	}

	@Override
	protected OverlayItem createItem(int i) {

		return mOverlays.get(i);
	}
}
