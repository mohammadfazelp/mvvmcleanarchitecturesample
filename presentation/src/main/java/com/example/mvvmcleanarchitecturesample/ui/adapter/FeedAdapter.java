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
import com.example.mvvmcleanarchitecturesample.ui.base.BaseRvAdapter;
import com.example.mvvmcleanarchitecturesample.utils.Utility;
import com.mvvmcleanarchitecturesample.data.utils.NetworkState;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class FeedAdapter extends BaseRvAdapter {

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private NetworkState networkState;

    @Inject
    public FeedAdapter(Context context, ArrayList items) {
        super(context, items);
    }

    @Override
    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
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

    @Override
    public void onBindData(RecyclerView.ViewHolder holder, Object val, int position) {

        if (holder instanceof ArticleItemViewHolder) {
            ((ArticleItemViewHolder) holder).bindTo(
                    (com.example.mvvmcleanarchitecturesample.model.ArticleModel) val);
        } else {
            ((NetworkStateItemViewHolder) holder).bindView(networkState);
        }
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

    private boolean hasExtraRow() {
        return networkState != null && networkState != NetworkState.LOADED;
    }

    /*
     * ViewHolder for the list item
     */
    public class ArticleItemViewHolder extends RecyclerView.ViewHolder {

        private FeedItemBinding binding;

        ArticleItemViewHolder(FeedItemBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(com.example.mvvmcleanarchitecturesample.model.ArticleModel article) {

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
     * ViewHolder for the progressView
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
