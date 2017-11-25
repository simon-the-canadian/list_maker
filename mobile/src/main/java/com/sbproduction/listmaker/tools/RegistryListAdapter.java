package com.sbproduction.listmaker.tools;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by simonbergeron on 11/25/17.
 */
public class RegistryListAdapter<T extends Object> extends RecyclerListAdapter<T>
{
	protected final SimpleArrayMap<Class, ViewHolderDelegate> classRegistry;
	protected final SimpleArrayMap<Integer, ViewHolderDelegate> typeRegistry;

	public RegistryListAdapter()
	{
		super();
		this.classRegistry = new SimpleArrayMap<>();
		this.typeRegistry = new SimpleArrayMap<>();
	}

	public RegistryListAdapter addDelegate(@NonNull ViewHolderDelegate delegate)
	{
		if (this.classRegistry.containsKey(delegate.getObjectType())) {
			throw new IllegalStateException("There is already a delegate in place for this type or resource.");
		}
		if (this.typeRegistry.containsKey(delegate.getLayoutResId())) {
			throw new IllegalStateException("There is already a delegate in place for this resource id.");
		}

		this.classRegistry.put(delegate.getObjectType(), delegate);
		this.typeRegistry.put(delegate.getLayoutResId(), delegate);

		return this;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		ViewHolderDelegate delegate = typeRegistry.get(viewType);

		if (delegate == null) {
			throw new IllegalStateException("There is no delegate for view type: " + viewType);
		}

		View view = LayoutInflater.from(parent.getContext()).inflate(delegate.getLayoutResId(), parent, false);

		return delegate.createViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
	{
		Object item = this.getItem(position);
		ViewHolderDelegate delegate = classRegistry.get(item.getClass());

		if (delegate == null) {
			for (Class<?> interfaceClass : item.getClass().getInterfaces()) {
				delegate = classRegistry.get(interfaceClass);

				if (delegate != null) {
					break;
				}
			}

			if (delegate == null) {
				throw new IllegalStateException("There is no delegate for type:" + item.getClass());
			}
		}

		//noinspection unchecked
		delegate.bindViewHolder(holder, item, position);
	}

	@Override
	public int getItemViewType(int position)
	{
		Object item = this.getItem(position);
		ViewHolderDelegate delegate = classRegistry.get(item.getClass());

		if (delegate == null) {
			for (Class<?> interfaceClass : item.getClass().getInterfaces()) {
				delegate = classRegistry.get(interfaceClass);

				if (delegate != null) {
					break;
				}
			}

			if (delegate == null) {
				throw new IllegalStateException("There is no delegate for type:" + item.getClass());
			}
		}

		return delegate.getLayoutResId();
	}

	public interface ViewHolderDelegate<DataType, VH extends RecyclerView.ViewHolder>
	{
		/**
		 * @return The DataType.class of the object this delegate is processing.
		 */
		Class getObjectType();

		/**
		 * @return Layout resource that will be used, This will ensure we don't collide with other registered handlers viewType
		 */
		@LayoutRes
		int getLayoutResId();

		/**
		 * @param view the view that was inflated for this view holder
		 * @return A new instance of the view holder of type {@code <VH>}
		 */
		VH createViewHolder(View view);

		/**
		 * @param holder   The view holder being recycled of type {@code <VH>}
		 * @param item     The object of type {@code <DataType>} being represented by the view
		 * @param position The position in the list
		 */
		void bindViewHolder(VH holder, DataType item, int position);
	}
}
