/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.hannesdorfmann.adapterdelegates.sample;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.hannesdorfmann.adapterdelegates.AdapterDelegatesManager;
import com.hannesdorfmann.adapterdelegates.sample.adapterdelegates.AdvertisementAdapterDelegate;
import com.hannesdorfmann.adapterdelegates.sample.adapterdelegates.CatAdapterDelegate;
import com.hannesdorfmann.adapterdelegates.sample.adapterdelegates.DogAdapterDelegate;
import com.hannesdorfmann.adapterdelegates.sample.adapterdelegates.GeckoAdapterDelegate;
import com.hannesdorfmann.adapterdelegates.sample.adapterdelegates.SnakeAdapterDelegate;
import com.hannesdorfmann.adapterdelegates.sample.model.DisplayableItem;
import java.util.List;

/**
 * @author Hannes Dorfmann
 */
public class MainAdapter extends RecyclerView.Adapter {

  private AdapterDelegatesManager<List<DisplayableItem>> delegatesManager;
  private List<DisplayableItem> items;

  public MainAdapter(Activity activity, List<DisplayableItem> items) {
    this.items = items;

    // Delegates
    delegatesManager = new AdapterDelegatesManager<>();
    delegatesManager.addDelegate(new AdvertisementAdapterDelegate(activity, 0));
    delegatesManager.addDelegate(new CatAdapterDelegate(activity, 1));
    delegatesManager.addDelegate(new DogAdapterDelegate(activity, 2));
    delegatesManager.addDelegate(new GeckoAdapterDelegate(activity, 3));
    delegatesManager.addDelegate(new SnakeAdapterDelegate(activity, 4));

  }

  @Override public int getItemViewType(int position) {
    return delegatesManager.getItemViewType(items, position);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return delegatesManager.onCreateViewHolder(parent, viewType);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    delegatesManager.onBindViewHolder(items, position, holder);
  }

  @Override public int getItemCount() {
    return items.size();
  }
}
