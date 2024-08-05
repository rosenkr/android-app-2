package se.umu.alro0113.trackandbet.transactions.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.LoadingDialog
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.MyTopAppBar
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.transactions.domain.model.Transaction
import se.umu.alro0113.trackandbet.util.BottomNavigationItem
import se.umu.alro0113.trackandbet.util.MyBottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    navController: NavHostController,
    viewModel: TransactionsViewModel = hiltViewModel()

){
    val state by viewModel.state.observeAsState()
    state?.let { state ->
        val pullToRefreshState = rememberPullToRefreshState()
        if(pullToRefreshState.isRefreshing){
            LaunchedEffect(true) {
                // todo this is basically our onRefresh below
                delay(1000)
                pullToRefreshState.endRefresh()
            }
        }

        val items = listOf(
            BottomNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home, Screen.HomeScreen),
            BottomNavigationItem("Transactions", Icons.Filled.Analytics, Icons.Outlined.Analytics, Screen.TransactionsScreen),
            BottomNavigationItem("Tickers", Icons.Filled.Search, Icons.Outlined.Search, Screen.TickersScreen)
        )

        val myPosition = 1

        LoadingDialog(isLoading = state.isLoading)
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                MyTopAppBar(title = "Transactions")
            },
            bottomBar = {
                MyBottomNavBar(items = items, navController = navController, selectedItemIndex = myPosition)
            }
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(top = it.calculateTopPadding())){
                OutlinedTextField(
                    value = state.searchQuery.toString(),
                    onValueChange = {
                        viewModel.onEvent(TransactionEvent.OnSearchQueryChange(it))
                    },
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                    ,
                    placeholder = {
                        Text(text = "Search") // doesnt work because I am working with INts, need to figure it out
                    },
                    maxLines = 1,
                    singleLine = true
                )


                Box(modifier = Modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()) {
                        items(state.transactions.size) { i ->
                            val transaction = state.transactions[i]
                            TransactionItem(
                                transaction = transaction,
                                modifier = Modifier.
                                fillMaxWidth().
                                padding(16.dp).
                                clickable {
                                    // TODO if weant to add navigation to somewhere
                                }
                            )
                            if(i < state.transactions.size){
                                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        }
                    }
                    PullToRefreshContainer(
                        modifier = Modifier.align(Alignment.TopCenter),
                        state = pullToRefreshState,
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionItem(
    transaction: Transaction,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = transaction.asset,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = transaction.date,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "(${transaction.isCompleted})"
            )
        }

    }
}
/*
Scaffold(
topBar = {
    TopAppBar(
        title = { Text("Title") },
        // Provide an accessible alternative to trigger refresh.
        actions = {
            IconButton(
                enabled = !viewModel.isRefreshing,
                onClick = { viewModel.refresh() },
            ) {
                Icon(Icons.Filled.Refresh, "Trigger Refresh")
            }
        },
    )
},
) {
    PullToRefreshBox(
        modifier = Modifier.padding(it),
        isRefreshing = viewModel.isRefreshing,
        onRefresh = { viewModel.refresh() },
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            if (!viewModel.isRefreshing) {
                items(viewModel.itemCount) {
                    ListItem({ Text(text = "Item ${viewModel.itemCount - it}") })
                }
            }
        }
    }
}*/