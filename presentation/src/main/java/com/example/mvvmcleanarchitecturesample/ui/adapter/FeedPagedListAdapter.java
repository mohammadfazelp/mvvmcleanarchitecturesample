package com.example.mvvmcleanarchitecturesample.ui.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmcleanarchitecturesample.R;
import com.example.mvvmcleanarchitecturesample.databinding.FeedItemBinding;
import com.example.mvvmcleanarchitecturesample.databinding.NetworkItemStateBinding;
import com.example.mvvmcleanarchitecturesample.model.ArticleModel;
import com.example.mvvmcleanarchitecturesample.utils.Utility;
import com.mvvmcleanarchitecturesample.data.utils.NetworkState;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class FeedPagedListAdapter extends PagedListAdapter<ArticleModel, RecyclerView.ViewHolder> {

    /*
     * There are two layout types we define
     * in this adapter:
     * 1. progrss view
     * 2. data view
     */

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private NetworkState networkState;

    /*
     * The DiffUtil is defined in the constructor
     */
    public FeedPagedListAdapter(Context context) {

        super(ArticleModel.DIFF_CALLBACK);
        this.context = context;
    }

    /*
     * Default method of RecyclerView.Adapter
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_PROGRESS) {
            NetworkItemStateBinding headerBinding = NetworkItemStateBinding.inflate(layoutInflater,
                    parent, false);
            return new NetworkStateItemViewHolder(headerBinding);

        } else {
            FeedItemBinding itemBinding = FeedItemBinding.inflate(layoutInflater,
                    parent, false);
            return new ArticleItemViewHolder(itemBinding);
        }
    }

    /*
     * Default method of RecyclerView.Adapter
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ArticleItemViewHolder) {
            ((ArticleItemViewHolder) holder).bindTo(getItem(position));
        } else {
            ((NetworkStateItemViewHolder) holder).bindView(networkState);
        }
    }

    private boolean hasExtraRow() {
        return networkState != null && networkState != NetworkState.LOADED;
    }

    /*
     * Default method of RecyclerView.Adapter
     */
    @Override
    public int getItemViewType(int position) {

        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {

        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }


    /*
     * We define A custom ViewHolder for the list item
     */
    public class ArticleItemViewHolder extends RecyclerView.ViewHolder {

        private FeedItemBinding binding;

        ArticleItemViewHolder(FeedItemBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(ArticleModel article) {

            binding.itemImage.setVisibility(View.VISIBLE);
            binding.itemDesc.setVisibility(View.VISIBLE);

            String author = article.getAuthor() == null || article.getAuthor().isEmpty() ?
                    context.getString(R.string.author_name) : article.getAuthor();
            String titleString = String.format(context.getString(R.string.item_title), author, article.getTitle());
            SpannableString spannableString = new SpannableString(titleString);
            spannableString.setSpan(
                    new ForegroundColorSpan(ContextCompat.getColor(context.getApplicationContext(), R.color.secondary_text)),
                    titleString.lastIndexOf(author) + author.length() + 1,
                    titleString.lastIndexOf(article.getTitle()) - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );

            binding.itemTitle.setText(spannableString);
            binding.itemTime.setText(String.format(context.getString(R.string.item_date),
                    Utility.getDate(article.getPublishedAt()), Utility.getTime(article.getPublishedAt())));
            binding.itemDesc.setText(article.getDescription());
            Picasso.get().load(article.getUrlToImage()).resize(250, 200).into(binding.itemImage);
        }
    }


    /*
     * We define A custom ViewHolder for the progressView
     */
    public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

        private NetworkItemStateBinding binding;

        NetworkStateItemViewHolder(NetworkItemStateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindView(NetworkState networkState) {

            if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }

            if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
                binding.errorMsg.setVisibility(View.VISIBLE);
                binding.errorMsg.setText(networkState.getMsg());
            } else {
                binding.errorMsg.setVisibility(View.GONE);
            }
        }
    }
}
