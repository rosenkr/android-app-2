package se.umu.alro0113.trackandbet.transactions.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import se.umu.alro0113.trackandbet.common.composables.PullToRefreshLazyColumn
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.LoadingDialog
import se.umu.alro0113.trackandbet.marketdata.presentation.util.components.MyTopAppBar
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.transactions.domain.model.Transaction
import se.umu.alro0113.trackandbet.util.BottomNavigationItem
import se.umu.alro0113.trackandbet.util.MyBottomNavBar

// did not use internal fun to separate viewmodel and state because want to access viewModel.onEvent() inside the screen
@Composable
fun TransactionsScreen(
    navController: NavHostController,
    viewModel: TransactionsViewModel = hiltViewModel()

){
    val items = listOf(
        BottomNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home, Screen.HomeScreen),
        BottomNavigationItem("Transactions", Icons.Filled.Analytics, Icons.Outlined.Analytics, Screen.TransactionsScreen),
        BottomNavigationItem("Tickers", Icons.Filled.Search, Icons.Outlined.Search, Screen.TickersScreen)
    )
    val myPosition = 1

    val state by viewModel.state.observeAsState()
    state?.let { state ->

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
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())){ // pad both ends to see all items
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = {
                        viewModel.onEvent(TransactionEvent.OnSearchQueryChange(it))
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                    ,
                    placeholder = {
                        Text(text = "Search")
                    },
                    maxLines = 1,
                    singleLine = true
                )

                PullToRefreshLazyColumn(
                    items = state.transactions,
                    content = { transaction ->
                        Column {
                            TransactionItem(
                                transaction = transaction,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp)
                                    .clickable {
                                        // TODO add navigation if wanted, although the transactions feature was just for learning, not really of interest
                                    }
                            )
                            // Check the index for adding divider
                            val index = state.transactions.indexOf(transaction)
                            if (index < state.transactions.size - 1) {
                                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        }
                    },
                    isRefreshing = state.isRefreshing, // todo isRefreshing ?
                    onRefresh = { viewModel.onEvent(TransactionEvent.Refresh) } )
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