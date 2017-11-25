package com.sbproduction.listmaker.tools;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.functions.Function2;

/**
 * Created by simonbergeron on 11/25/17.
 */
public abstract class RecyclerListAdapter<T extends Object> extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	protected List<T> items;
	protected View.OnClickListener itemClickListener;

	/**
	 * @see android.support.v7.widget.RecyclerView.Adapter
	 */
	@Override
	public int getItemCount()
	{
		return items == null ? 0 : items.size();
	}

	/**
	 * Returns the item at a given position.
	 *
	 * @param position the position of the item
	 * @return the object associated with a given position in the list
	 */
	public T getItem(int position)
	{
		return items.get(position);
	}

	/**
	 * Gets the backing list of items used by this adapter.
	 *
	 * @return the list of items
	 */
	@Nullable
	public List<T> getItems()
	{
		return items;
	}

	/**
	 * Replaces the backing list of the RecyclerView.
	 *
	 * @param modelList the list of items that will be shown in the RecyclerView
	 */
	public void setItems(@NonNull List<T> modelList)
	{
		items = modelList;
	}

	/**
	 * Adds a new model at the end of the list.
	 *
	 * @param modelItem The new model to add
	 */
	public void addItem(@NonNull T modelItem)
	{
		if (items == null) {
			items = new ArrayList<>();
		}
		items.add(modelItem);
		notifyItemInserted(items.size() - 1);
	}

	/**
	 * Adds a new model at the specified position in the list.
	 *
	 * @param modelItem The new model to add
	 * @param position  The position to add the model to: 0 - n-1
	 */
	public void addItem(@NonNull T modelItem, int position)
	{
		if (items == null) {
			items = new ArrayList<>();
		}
		items.add(position, modelItem);
		notifyItemInserted(position);
	}

	/**
	 * Removes the model at the specified position from the list.
	 *
	 * @param position The position to remove the model from: 0 - n-1
	 */
	public void removeItem(int position)
	{
		if (items != null) {
			T removed = items.remove(position);
			if (removed != null) {
				notifyItemRemoved(position);
			}
		}
	}

	public boolean removeItem(@NonNull T item)
	{
		if (items != null) {
			int index = items.indexOf(item);
			if (index >= 0) {
				boolean result = items.remove(item);
				if (result) {
					notifyItemRemoved(index);
				}

				return result;
			}
		}

		return false;
	}

	/**
	 * Places the item at the specified position with the new model.
	 *
	 * @param modelItem The new model to add
	 * @param position  The position to replace with the new model: 0 - n-1
	 */
	public void replaceItem(@NonNull T modelItem, int position)
	{
		if (items != null) {
			items.remove(position);
			items.add(position, modelItem);
			notifyItemChanged(position);
		}
	}

	/**
	 * Replaces the backing list of the RecyclerView. Notifies the adapter to refresh all list items.
	 *
	 * @param modelList the list of items that will be shown in the RecyclerView
	 */
	public void replaceAll(@NonNull List<T> modelList)
	{
		items = new ArrayList<>(modelList);
		notifyDataSetChanged();
	}

	/**
	 * Replaces the backing list of the RecyclerView. Does a diff and updates the list content.
	 *
	 * @param modelList       the list of items that will be shown in the RecyclerView
	 * @param diffUtilFactory factory to inform if content has changed or if it is the same
	 */
	public void replaceAll(@NonNull List<T> modelList, @NonNull Function2<List<T>, List<T>, DiffUtil.Callback> diffUtilFactory)
	{
		List<T> updatedList = new ArrayList<>(modelList);

		if (items == null) {
			items = updatedList;
			notifyDataSetChanged();
		}
		else {
			DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffUtilFactory.invoke(items, updatedList));
			setItems(updatedList);
			result.dispatchUpdatesTo(this);
		}
	}
}
